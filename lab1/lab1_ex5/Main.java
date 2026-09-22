import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Print Multiplication table of n */
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();

        for(int i = 1; i <= 10; ++i) {
            System.out.println(n + " x " + i + " = " + n*i);
        }

        scanner.close();
    }
}
