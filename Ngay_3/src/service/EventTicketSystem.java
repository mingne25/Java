package service;

import model.Event;
import model.Booking;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTicketSystem {
    private List<Event> events;
    private LinkedList<Booking> bookings;
    private Set<String> bookedSeats;
    private Map<String, Integer> bookingStats;

    public EventTicketSystem() {
        events = new ArrayList<>();
        bookings = new LinkedList<>();
        bookedSeats = new HashSet<>();
        bookingStats = new HashMap<>();
    }

    public void addEvent(String eventId, String eventName, String location, LocalDate date, int maxSeats) {
        events.add(new Event(eventId, eventName, location, date, maxSeats));
    }

    public boolean bookTicket(String userEmail, String eventId, String seatNumber) {
        String seatKey = eventId + "-" + seatNumber;
        if (bookedSeats.contains(seatKey)) {
            System.out.println("Chỗ ngồi đã được đặt!");
            return false;
        }

        bookings.add(new Booking(userEmail, eventId, seatNumber));
        bookedSeats.add(seatKey);
        bookingStats.put(eventId, bookingStats.getOrDefault(eventId, 0) + 1);
        return true;
    }

    public void printBookingStats() {
        System.out.println("\nThống kê đặt vé theo sự kiện:");
        bookingStats.forEach((eventId, count) ->
                System.out.println("Event ID: " + eventId + " - Số vé đã đặt: " + count));
    }

    public void searchAndSortEventsByName(String keyword) {
        System.out.println("\nKết quả tìm kiếm cho '" + keyword + "':");
        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : events) {
            if (event.getEventName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredEvents.add(event);
            }
        }

        filteredEvents.sort((e1, e2) -> e1.getEventName().compareToIgnoreCase(e2.getEventName()));
        filteredEvents.forEach(System.out::println);
    }

    public void printAllBookings() {
        System.out.println("\nDanh sách vé đã đặt (theo thứ tự đặt):");
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void runInteractiveMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== HỆ THỐNG ĐẶT VÉ SỰ KIỆN ===");
            System.out.println("1. Thêm sự kiện mới");
            System.out.println("2. Đặt vé");
            System.out.println("3. Xem danh sách sự kiện");
            System.out.println("4. Tìm kiếm sự kiện theo tên");
            System.out.println("5. Xem danh sách vé đã đặt");
            System.out.println("6. Xem thống kê đặt vé");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID sự kiện: ");
                        String eventId = scanner.nextLine();
                        System.out.print("Nhập tên sự kiện: ");
                        String eventName = scanner.nextLine();
                        System.out.print("Nhập địa điểm: ");
                        String location = scanner.nextLine();
                        System.out.print("Nhập ngày (dd/MM/yyyy): ");
                        String dateStr = scanner.nextLine();
                        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        System.out.print("Nhập số chỗ tối đa: ");
                        int maxSeats = Integer.parseInt(scanner.nextLine());
                        addEvent(eventId, eventName, location, date, maxSeats);
                        System.out.println("Đã thêm sự kiện thành công!");
                        break;

                    case 2:
                        System.out.print("Nhập email: ");
                        String email = scanner.nextLine();
                        System.out.print("Nhập ID sự kiện: ");
                        String bookEventId = scanner.nextLine();
                        System.out.print("Nhập số ghế: ");
                        String seat = scanner.nextLine();
                        if (bookTicket(email, bookEventId, seat)) {
                            System.out.println("Đặt vé thành công!");
                        }
                        break;

                    case 3:
                        System.out.println("\nDanh sách sự kiện:");
                        events.forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Nhập từ khóa tìm kiếm: ");
                        String keyword = scanner.nextLine();
                        searchAndSortEventsByName(keyword);
                        break;

                    case 5:
                        printAllBookings();
                        break;

                    case 6:
                        printBookingStats();
                        break;

                    case 0:
                        System.out.println("Cảm ơn đã sử dụng hệ thống!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu: " + e.getMessage());
            }
        }
    }
}