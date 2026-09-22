import java.util.Scanner;

public class Main {
    /* Even or odd numbers */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();

        for(int i = 1; i <= n; ++i) {
            String k = "Odd";
            if(i % 2 == 0) k = "Even";

            System.out.println(i + " - " + k);
        }

        scanner.close();
    }
}
