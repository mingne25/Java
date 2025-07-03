import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private final Semaphore transactionLimiter = new Semaphore(10);
    private final AtomicInteger successfulTransactions = new AtomicInteger(0);

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public Account getAccount(int id) {
        return accounts.get(id);
    }

    public int getTotalBalance() {
        return accounts.stream().mapToInt(Account::getBalance).sum();
    }

    public List<Account> getRichAccounts(int threshold) {
        return accounts.parallelStream()
                .filter(a -> a.getBalance() > threshold)
                .toList();
    }

    public void transfer(Account from, Account to, int amount) throws InterruptedException {
        if (from.getId() == to.getId()) return;

        transactionLimiter.acquire();

        List<Account> lockOrder = from.getId() < to.getId() ? List.of(from, to) : List.of(to, from);

        try {
            for (Account acc : lockOrder) acc.getLock().lock();

            if (!from.withdraw(amount)) {
                System.out.println("Tài khoản " + from.getId() + " không đủ tiền để chuyển " + amount + " VND.");
                return;
            }

            to.deposit(amount);
            successfulTransactions.incrementAndGet();

            AsyncService.sendConfirmationEmail(from.getId(), to.getId(), amount);

        } finally {
            for (Account acc : lockOrder) acc.getLock().unlock();
            transactionLimiter.release();
        }
    }

    public int getSuccessfulTransactionCount() {
        return successfulTransactions.get();
    }
}
