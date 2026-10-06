public class Main {
    public static void main(String[] args) {
        Time time1 = new Time(23, 59, 50);
        time1.display();
        System.out.print(" + 20 = ");
        time1.addSeconds(20).display();

        System.out.println();

        Time time2 = new Time(0, 0, 10);
        time2.display();
        System.out.print(" - 20 = ");
        time2.subtractSeconds(20).display();
    }
}
