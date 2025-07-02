import service.EventTicketSystem;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EventTicketSystem system = new EventTicketSystem();

        // Thêm sự kiện mẫu
        system.addEvent("E001", "Hội thảo Công nghệ", "Hà Nội",
                LocalDate.of(2023, 12, 15), 100);
        system.addEvent("E002", "Workshop Lập trình", "TP.HCM",
                LocalDate.of(2023, 11, 20), 50);
        system.addEvent("E003", "Turtorial PHP", "Đà Nẵng",
                LocalDate.of(2024, 1, 10), 200);

        // Chạy menu tương tác
        system.runInteractiveMenu();
    }
}