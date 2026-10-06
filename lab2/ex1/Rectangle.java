public class Rectangle {
    // 1. Attributes
    private double width, height;

    // 2. No-argument constructor
    public Rectangle() {
        this.width = 1;
        this.height = 1;
    }

    // 3. Parameterized constructor
    public Rectangle(double width, double height) {
        if(width <= 0) width = 1;
        if(height <= 0) height = 1;
        this.width = width;
        this.height = height;
    }
    
    public double area() {
        return width * height;
    }

    public double perimeter() {
        return (width + height) * 2;
    }

    public void displayInfo() {
        System.out.println("Size: " + width + "x" + height + "; Area = " + area() + "; Perimeter = " + perimeter());
    }
}