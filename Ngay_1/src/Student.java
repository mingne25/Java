public class Student {
    private static int nextId = 1; // Tự động tăng id
    private int id;
    private String name;
    private int age;
    private double score;

    public Student(String name, int age, double score) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // Getter
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    // Overloaded method
    public void printStudent() {
        System.out.printf("ID: %d | Name: %s | Age: %d | Score: %.2f\n", id, name, age, score);
    }

    public void printStudent(boolean highlight) {
        if (highlight)
            System.out.println("*** High Score Student ***");
        printStudent();
    }
}
