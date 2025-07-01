import model.*;
import interfaces.Printable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ TÀI KHOẢN NGÂN HÀNG ===");
            System.out.println("1. Tạo tài khoản mới");
            System.out.println("2. Gửi tiền");
            System.out.println("3. Rút tiền");
            System.out.println("4. Xem thông tin tài khoản");
            System.out.println("5. Xem lịch sử giao dịch");
            System.out.println("6. Tính lãi (tài khoản tiết kiệm)");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performTransaction(TransactionType.DEPOSIT);
                    break;
                case 3:
                    performTransaction(TransactionType.WITHDRAW);
                    break;
                case 4:
                    viewAccountInfo();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    calculateInterest();
                    break;
                case 7:
                    running = false;
                    System.out.println("Cảm ơn bạn đã sử dụng dịch vụ!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void createAccount() {
        System.out.println("\n=== TẠO TÀI KHOẢN MỚI ===");
        System.out.print("Nhập loại tài khoản (1 - Tiết kiệm, 2 - Thanh toán): ");
        int accountType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nNhập thông tin khách hàng:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Họ tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Số điện thoại: ");
        String phoneNumber = scanner.nextLine();

        Person owner = new Person(id, fullName, email, phoneNumber);

        System.out.print("Số tài khoản: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Số dư ban đầu: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        LocalDate createdDate = LocalDate.now();

        if (accountType == 1) {
            System.out.print("Lãi suất (%): ");
            double interestRate = scanner.nextDouble();
            scanner.nextLine();
            SavingsAccount account = new SavingsAccount(accountNumber, owner, balance, createdDate, interestRate);
            accounts.add(account);
            System.out.println("Tạo tài khoản tiết kiệm thành công!");
        } else if (accountType == 2) {
            System.out.print("Hạn mức thấu chi: ");
            double overdraftLimit = scanner.nextDouble();
            scanner.nextLine();
            CurrentAccount account = new CurrentAccount(accountNumber, owner, balance, createdDate, overdraftLimit);
            accounts.add(account);
            System.out.println("Tạo tài khoản thanh toán thành công!");
        } else {
            System.out.println("Loại tài khoản không hợp lệ!");
        }
    }

    private static void performTransaction(TransactionType type) {
        System.out.print("\nNhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản!");
            return;
        }

        System.out.print("Nhập số tiền: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (type == TransactionType.DEPOSIT) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private static void viewAccountInfo() {
        System.out.print("\nNhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản!");
            return;
        }

        account.printSummary();
    }

    private static void viewTransactionHistory() {
        System.out.print("\nNhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản!");
            return;
        }

        account.printTransactionHistory();
    }

    private static void calculateInterest() {
        System.out.print("\nNhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản!");
            return;
        }

        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).calculateInterest();
        } else {
            System.out.println("Chỉ áp dụng cho tài khoản tiết kiệm!");
        }
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {  // Sử dụng getter thay vì truy cập trực tiếp
                return account;
            }
        }
        return null;
    }
}