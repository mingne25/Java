import java.util.Random;

public class TransactionTask implements Runnable {
    private final Bank bank;
    private final Random random = new Random();

    public TransactionTask(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int fromId = random.nextInt(100);
                int toId = random.nextInt(100);
                int amount = random.nextInt(500);

                Account from = bank.getAccount(fromId);
                Account to = bank.getAccount(toId);

                bank.transfer(from, to, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
