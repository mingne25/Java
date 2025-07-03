import java.util.concurrent.CompletableFuture;

public class AsyncService {
    public static void sendConfirmationEmail(int fromId, int toId, int amount) {
        CompletableFuture.runAsync(() -> {
            System.out.printf("📧 Email: Người dùng ID:%d gửi %d.000 VNĐ đến Người dùng ID:%d%n", fromId, amount, toId);
        });
    }
}
