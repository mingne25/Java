package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, Person owner, double balance, LocalDate createdDate, double overdraftLimit) {
        super(accountNumber, owner, balance, createdDate, AccountType.CURRENT);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Số tiền rút phải lớn hơn 0");
            return;
        }
        if (amount > (balance + overdraftLimit)) {
            System.out.println("Vượt quá hạn mức cho phép");
            return;
        }
        this.balance -= amount;
        String transactionId = "TXN" + System.currentTimeMillis();
        transactions.add(new Transaction(transactionId, TransactionType.WITHDRAW, amount, LocalDateTime.now()));
        System.out.println("Rút tiền thành công. Số dư mới: " + this.balance);
    }

    @Override
    public void printAccountInfo() {
        super.printAccountInfo();
        System.out.println("Hạn mức thấu chi: " + overdraftLimit);
    }
}