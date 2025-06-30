import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: addStudent(); break;
                case 2: showAllStudents(); break;
                case 3: searchByName(); break;
                case 4: showHighestScore(); break;
                case 5: sortByScoreDesc(); break;
                case 6: calculateAverageScore(); break;
                case 7: factorialOfFirstStudentAge(); break;
                case 0: System.out.println("Thoát chương trình."); break;
                default: System.out.println("Chức năng không hợp lệ.");
            }
        } while (choice != 0);
    }

    static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Hiển thị danh sách");
        System.out.println("3. Tìm theo tên");
        System.out.println("4. Sinh viên điểm cao nhất");
        System.out.println("5. Sắp xếp theo điểm giảm dần");
        System.out.println("6. Tính điểm trung bình");
        System.out.println("7. Giai thừa tuổi SV đầu tiên");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    static void addStudent() {
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        int age;
        while (true) {
            System.out.print("Nhập tuổi (>0): ");
            age = Integer.parseInt(sc.nextLine());
            if (age > 0) break;
            System.out.println("Tuổi phải > 0.");
        }

        double score;
        while (true) {
            System.out.print("Nhập điểm (0-10): ");
            score = Double.parseDouble(sc.nextLine());
            if (score >= 0 && score <= 10) break;
            System.out.println("Điểm phải từ 0 đến 10.");
        }

        students.add(new Student(name, age, score));
        System.out.println("Đã thêm sinh viên!");
    }

    static void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        for (Student s : students) {
            s.printStudent();
        }
    }

    static void searchByName() {
        System.out.print("Nhập tên cần tìm: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(keyword)) {
                s.printStudent();
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy sinh viên nào.");
    }

    static void showHighestScore() {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        Student maxStudent = Collections.max(students, Comparator.comparingDouble(Student::getScore));
        maxStudent.printStudent(true);
    }

    static void sortByScoreDesc() {
        students.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        System.out.println("Đã sắp xếp theo điểm giảm dần.");
    }

    static void calculateAverageScore() {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getScore();
        }
        double avg = sum / students.size();
        System.out.printf("Điểm trung bình: %.2f\n", avg);
    }

    static void factorialOfFirstStudentAge() {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        int age = students.get(0).getAge();
        long result = factorial(age);
        System.out.printf("Giai thừa của tuổi (%d) là: %d\n", age, result);
    }

    // Đệ quy tính giai thừa
    static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}

