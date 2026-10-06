public class Circle {
    // 1. Attributes
    private double centerX, centerY, radius;
    private final double pi = 3.14;

    // 2. Default constructor
    public Circle() {
        centerX = 0;
        centerY = 0;
        radius = 1;
    }

    // 3. Parameterized constructor
    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public double area() {
        return pi * radius * radius;
    }

    public double perimeter() {
        return pi * 2 * radius;
    }

    public boolean contains(double x, double y) {
        double length = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        return length <= radius;
    }
}
