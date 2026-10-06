public class Main {
    public static void main(String[] args) {
        // Default constructor
        Rectangle A = new Rectangle();
        System.out.println("Rectangle A area = " + A.area());

        // Parameterized constructor
        Rectangle B = new Rectangle(2.1, 5.6);
        System.out.println("Rectangle B perimeter = " + B.perimeter());

        // Invalid arguments
        Rectangle C = new Rectangle(5, -1);
        System.out.print("Rectangle C information: ");
        C.displayInfo();
    }
}
