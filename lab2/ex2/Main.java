public class Main {
    public static void main(String[] args) {
        // Valid construction
        Fraction A = new Fraction(4, 5);
        System.out.print("Fraction A = ");
        A.display();

        // Invalid construction (denominator = 0)
        Fraction B = new Fraction(3, 0);
        System.out.print("Fraction B = ");
        B.display();

        // Calculate sum of A & B and assign using Copy Constructor
        Fraction C = new Fraction(A.add(B));
        System.out.print("Fraction C = ");
        C.display();
    }
}
