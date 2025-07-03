import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final int id;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() { return id; }
    public int getBalance() { return balance; }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
