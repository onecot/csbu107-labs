public class Main {
    public static void main(String[] args) {
        UITStudent s1 = new UITStudent();
        UITStudent s2 = new UITStudent("123456", "Bui Minh O");
        UITStudent s3 = new UITStudent("234567", "Nguyen Van A", "SE", 4.5);
        UITStudent s4 = new UITStudent("345678", "Tran Quoc B", "CS", 6.5);
        UITStudent s5 = new UITStudent("456789", "Mai Bao C", "IS", 8.5);
        UITStudent s6 = new UITStudent("567890", "Pham Thi D", "CE", 7.3);
        UITStudent s7 = new UITStudent("678901", "Le Minh Tan", "CS", 9.38);

        UITStudent[] students = {s1, s2, s3, s4, s5, s6, s7};
        for (UITStudent s : students) {
            s.displayInfo();
            System.out.println(" → Passed: " + s.isPassed() + "\n");
        }
    }
}