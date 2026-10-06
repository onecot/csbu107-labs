public class Main {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(0, 0, 5);
        Circle c3 = new Circle(2, 3, 2);

        double[][] pts = {{0,0}, {1,0}, {5,0}, {6,0}, {2,3}, {3,4}};
        Circle[] cs = {c1, c2, c3};

        for (int i = 0; i < cs.length; i++) {
            System.out.println("Circle " + (i+1) + " area=" + cs[i].area());
            for (double[] p : pts)
                System.out.println("  (" + p[0] + "," + p[1] + ") inside? " + cs[i].contains(p[0], p[1]));
        }
    }
}
