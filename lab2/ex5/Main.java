public class Main {
    public static void main(String[] args) {
        Dice d = new Dice();
        int n = 6000;
        int[] freq = new int[7];

        for(int i = 0; i < n; ++i) {
            int v = d.roll();
            freq[v]++;
        }

        for(int face = 1; face <= 6; face++) {
            double prob = (double)freq[face] / n;
            System.out.println("Probability of " + face + " = " + prob);
        }
    }
}
