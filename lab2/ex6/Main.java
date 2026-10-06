public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("add(2,3) = " + calc.add(2,3) + "        → add(int,int)");
        System.out.println("add(2.5,3.1) = " + calc.add(2.5,3.1) + "  → add(double,double)");
        System.out.println("add(1,2,3) = " + calc.add(1,2,3) + "      → add(int,int,int)");
        System.out.println("max(5,9) = " + calc.max(5,9) + "        → max(int,int)");
        System.out.println("max(4.2,7.8) = " + calc.max(4.2,7.8) + "  → max(double,double)");
    }
}
