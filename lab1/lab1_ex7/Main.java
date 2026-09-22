import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Merge two sorted arrays */
        Scanner scanner = new Scanner(System.in);

        // Enter first array
        System.out.print("Enter 1st sorted array length: ");
        int n = scanner.nextInt();
        System.out.print("Enter 1st sorted array (" + n + " elements): ");
        int[] arr1 = new int[n];
        for(int i = 0; i < n; ++i)
            arr1[i] = scanner.nextInt();

        // Enter second array
        System.out.print("Enter 2nd sorted array length: ");
        int m = scanner.nextInt();
        System.out.print("Enter 2nd sorted array (" + m + " elements): ");
        int[] arr2 = new int[m];
        for(int i = 0; i < m; ++i)
            arr2[i] = scanner.nextInt();
        
        // Merge two arrays
        int i = 0, j = 0, k = 0;
        int[] merged_arr = new int[m + n];
        while(i < n && j < m) {
            if(arr1[i] < arr2[j]) {
                merged_arr[k] = arr1[i];
                i += 1;
            } else {
                merged_arr[k] = arr2[j];
                j += 1;
            }
            k++;
        }

        for(; i < n; ++i, ++k) merged_arr[k] = arr1[i];
        for(; j < m; ++j, ++k) merged_arr[k] = arr2[j];

        // Print merged array
        System.out.print("Merged array: ");
        for(i = 0; i < k; ++i) {
            System.out.print(merged_arr[i] + " ");
        }

        scanner.close();
    }
}
