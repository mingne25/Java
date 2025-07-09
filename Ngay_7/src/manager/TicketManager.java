package manager;

import exception.InvalidUserInfoException;
import exception.PaymentGatewayException;
import exception.TicketSoldOutException;
import model.TicketType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketManager {
    private final Map<TicketType, Integer> ticketInventory;
    private final List<String> bookingLogs;

    public TicketManager() {
        ticketInventory = new ConcurrentHashMap<>();
        for (TicketType type : TicketType.values()) {
            ticketInventory.put(type, 100);
        }
        bookingLogs = new ArrayList<>();
    }

    public void datVe(String tenNguoiDung, TicketType loaiVe)
            throws InvalidUserInfoException, TicketSoldOutException, PaymentGatewayException {

        if (tenNguoiDung == null || tenNguoiDung.isBlank()) {
            throw new InvalidUserInfoException("Thông tin người dùng không hợp lệ.");
        }

        try {
            xuLyThanhToan();
        } catch (Exception e) {
            throw new PaymentGatewayException("Kết nối cổng thanh toán thất bại.", e);
        }

        synchronized (this) {
            int soLuongConLai = ticketInventory.getOrDefault(loaiVe, 0);
            if (soLuongConLai <= 0) {
                throw new TicketSoldOutException("Đã hết vé cho loại: " + loaiVe.getTenHienThi());
            }
            ticketInventory.put(loaiVe, soLuongConLai - 1);
            bookingLogs.add("Người dùng: " + tenNguoiDung + " đã đặt vé " + loaiVe.getTenHienThi());
        }
    }

    private void xuLyThanhToan() throws Exception {
        try (AutoCloseable resource = () -> System.out.println("Đóng tài nguyên")) {
            if (Math.random() < 0.1) {
                throw new Exception("Lỗi kết nối.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void inThongKeVe() {
        System.out.println("\n=== THỐNG KÊ VÉ ===");
        ticketInventory.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry ->
                        System.out.println(entry.getKey().getTenHienThi() + ": " + entry.getValue()));
    }

    public void moPhongDatVeNhieuNguoi() {
        Runnable task = () -> {
            try {
                datVe(Thread.currentThread().getName(), TicketType.VIP);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Thread t = new Thread(task, "NguoiDung-" + i);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("\n=== DANH SÁCH ĐẶT VÉ ===");
        bookingLogs.forEach(System.out::println);
    }
}