import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Find an Element in an Array */
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter array length: ");
        int n = scanner.nextInt();
        
        System.out.print("Enter array of " + n + " elements: ");
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i)
            arr[i] = scanner.nextInt();
        
        System.out.print("Enter search number: ");
        int x = scanner.nextInt();

        int foundIndex = -1;
        for(int i = 0; i < n; ++i)
            if(arr[i] == x) {
                foundIndex = i;
                break;
            }
        
        System.out.println("Found index: " + foundIndex);

        scanner.close();
    }
}
