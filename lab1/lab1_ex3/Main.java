import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Sum of Digits */
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        int sumDigits = 0;
        while(num > 0) {
            int digit = num % 10;
            sumDigits += digit;
            num /= 10;
        }

        System.out.println(sumDigits);

        scanner.close();
    }
}