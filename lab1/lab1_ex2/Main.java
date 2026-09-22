import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Find the largest number of three */

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();
        System.out.print("Enter third number: ");
        int num3 = scanner.nextInt();

        if(num1 >= num2 && num1 >= num3) {
            System.out.println(num1);
        } else if(num2 >= num1 && num2 >= num3) {
            System.out.println(num2);
        } else {
            System.out.println(num3);
        }

        scanner.close();
    }
}
