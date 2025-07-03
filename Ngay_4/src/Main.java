import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();

        for (int i = 0; i < 100; i++) {
            bank.addAccount(new Account(i, 10_000));
        }

        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            executor.submit(new TransactionTask(bank));
        }

        executor.shutdown();
        executor.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS);

        System.out.println("Tổng số giao dịch thành công: " + bank.getSuccessfulTransactionCount());
        System.out.println("Tổng số tiền còn lại trong ngân hàng: " + bank.getTotalBalance());

        System.out.println("Các tài khoản có số dư lớn hơn 20.000:");
        for (Account acc : bank.getRichAccounts(20000)) {
            System.out.printf(" - Tài khoản %d: %d VND%n", acc.getId(), acc.getBalance());
        }
    }
}
