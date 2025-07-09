import manager.TicketManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG ĐẶT VÉ SỰ KIỆN TRỰC TUYẾN ===");

        TicketManager manager = new TicketManager();
        manager.inThongKeVe();
        manager.moPhongDatVeNhieuNguoi();
        manager.inThongKeVe();
    }
}