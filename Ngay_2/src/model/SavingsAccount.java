package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, Person owner, double balance, LocalDate createdDate, double interestRate) {
        super(accountNumber, owner, balance, createdDate, AccountType.SAVINGS);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Số tiền rút phải lớn hơn 0");
            return;
        }
        if (amount > balance) {
            System.out.println("Số dư không đủ để rút");
            return;
        }
        if (balance - amount < 50000) {
            System.out.println("Số dư tối thiểu phải là 50,000 VND");
            return;
        }
        this.balance -= amount;
        String transactionId = "TXN" + System.currentTimeMillis();
        transactions.add(new Transaction(transactionId, TransactionType.WITHDRAW, amount, LocalDateTime.now()));
        System.out.println("Rút tiền thành công. Số dư mới: " + this.balance);
    }

    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest);
        System.out.println("Tiền lãi đã được cộng vào tài khoản: " + interest);
    }

    @Override
    public void printAccountInfo() {
        super.printAccountInfo();
        System.out.println("Lãi suất: " + interestRate + "%");
    }
}