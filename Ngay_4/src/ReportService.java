import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ReportService {
    private final Bank bank;

    public ReportService(Bank bank) {
        this.bank = bank;
    }

    public void generateReport() {
        ForkJoinPool.commonPool().submit(() -> {
            System.out.println("Bắt đầu tạo báo cáo...");

            int totalBalance = bank.getTotalBalance();
            System.out.println("Tổng số tiền còn lại trong ngân hàng: " + totalBalance);

            List<Account> richAccounts = bank.getRichAccounts(20000);
            System.out.println("Các tài khoản có số dư > 20,000:");
            for (Account acc : richAccounts) {
                System.out.printf(" - Tài khoản %d: %d VND%n", acc.getId(), acc.getBalance());
            }

            System.out.println("📊 Báo cáo hoàn tất.");

        }).join(); // Đợi kết thúc báo cáo
    }
}
