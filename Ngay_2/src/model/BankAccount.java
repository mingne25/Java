package model;

import interfaces.Printable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class BankAccount implements Printable {
    protected String accountNumber;
    protected Person owner;
    protected double balance;
    protected LocalDate createdDate;
    protected AccountType accountType;
    protected ArrayList<Transaction> transactions;

    protected class Transaction {
        private String id;
        private TransactionType type;
        private double amount;
        private LocalDateTime timestamp;

        public Transaction(String id, TransactionType type, double amount, LocalDateTime timestamp) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id='" + id + '\'' +
                    ", type=" + type +
                    ", amount=" + amount +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public BankAccount(String accountNumber, Person owner, double balance, LocalDate createdDate, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.createdDate = createdDate;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Số tiền gửi phải lớn hơn 0");
            return;
        }
        this.balance += amount;
        String transactionId = "TXN" + System.currentTimeMillis();
        transactions.add(new Transaction(transactionId, TransactionType.DEPOSIT, amount, LocalDateTime.now()));
        System.out.println("Gửi tiền thành công. Số dư mới: " + this.balance);
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public void printAccountInfo() {
        System.out.println("Số tài khoản: " + accountNumber);
        System.out.println("Chủ tài khoản: " + owner.getFullName());
        System.out.println("Loại tài khoản: " + accountType);
        System.out.println("Số dư: " + balance);
        System.out.println("Ngày tạo: " + createdDate);
    }

    @Override
    public void printSummary() {
        System.out.println("=== Tóm tắt tài khoản ===");
        printAccountInfo();
        System.out.println("Tổng số giao dịch: " + transactions.size());
    }

    public void printTransactionHistory() {
        System.out.println("=== Lịch sử giao dịch ===");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}