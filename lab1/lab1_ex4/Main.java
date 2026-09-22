import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Reverse a String */
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        String reversed_str = "";

        for(int i = str.length() - 1; i >= 0; --i) {
            reversed_str += str.charAt(i);
        }

        System.out.println(reversed_str);

        scanner.close();
    }
}