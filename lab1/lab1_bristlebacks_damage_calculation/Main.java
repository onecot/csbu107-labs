import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Bristleback's Quill Spray Damage Calculation */

        Scanner scanner = new Scanner(System.in);

        // Input timestamps
        int n = scanner.nextInt();
        double[] times = new double[n];
        for(int i = 0; i < n; ++i)
            times[i] = scanner.nextDouble();

        // Input base damage, additional damage & disappear duration
        double base_dmg = scanner.nextDouble();
        double add_dmg = scanner.nextDouble();
        double disappear_dur = scanner.nextDouble();

        double total_dmg = calculateDamage(n, times, base_dmg, add_dmg, disappear_dur);
        System.out.println(total_dmg);

        scanner.close();
    }

    static double calculateDamage(int n, double[] times, double base_dmg, double add_dmg, double disappear_dur) {
        double total_dmg = 0.0;
        int last_active_quill_idx = 0;

        for(int i = 0; i < n; ++i) {
            total_dmg += base_dmg;
            // System.out.print("current time: " + times[i]);
            while(
                last_active_quill_idx < n &&
                times[i] - times[last_active_quill_idx] > disappear_dur
            ) {
                last_active_quill_idx += 1;
            }
            
            if(last_active_quill_idx < n) {
                total_dmg += add_dmg * (i - last_active_quill_idx);
                // System.out.print(" - last quill to be appeared time: " + times[last_active_quill_idx]);
            }
            // System.out.println();
        }
        
        return total_dmg;
    }
}

/*
TEST CASE 1:
Input:
5
4.9 12.6 19.1 22.9 46.2
65
51
15.0
Output:
580

TEST CASE 2:
Input:
13
4.9 12.6 19.1 22.9 26.2 32.7 34.6 44.3
44.6 50.5 59.8 66.2 72.1
65
51
15.0
Output:
2120
*/