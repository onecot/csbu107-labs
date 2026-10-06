public class Fraction {
    // 1. Attributes
    private int numerator, denominator;

    // 2. No-argument constructor
    public Fraction() {
        this.numerator = 0;
        this.denominator = 1;
    }

    // 3. Parameterized constructor
    public Fraction(int numerator, int denominator) {
        // Denominator must be non-zero
        if(denominator == 0) {
            System.out.println("Denominator must be non-zero! Auto set denominator to 1.");
            denominator = 1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // 4. Copy constructor
    public Fraction(Fraction other) {
        this.numerator = other.numerator;
        this.denominator = other.denominator;
    }

    public Fraction simplify() {
        if(numerator == 0) return new Fraction(this);

        int gcd = (numerator > 0) ? numerator : -numerator;
        int simpleNumerator = this.numerator, simpleDenominator = this.denominator;
        while(gcd > 0 && !(simpleNumerator % gcd == 0 && simpleDenominator % gcd == 0)) {
            gcd--;
        }
        
        if(gcd > 0 && simpleNumerator % gcd == 0 && simpleDenominator % gcd == 0) {
            simpleNumerator /= gcd;
            simpleDenominator /= gcd;
        }

        return new Fraction(simpleNumerator, simpleDenominator);
    }

    public Fraction add(Fraction other) {
        // Same denominator, only add numerators
        if(this.denominator == other.denominator) {
            return new Fraction(this.numerator + other.numerator, this.denominator);
        }

        // Finding common denominator
        int calculatedNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int calculatedDenominator = this.denominator * other.denominator;

        return new Fraction(calculatedNumerator, calculatedDenominator);
    }

    public Fraction subtract(Fraction other) {
        // Same denominator, only subtract numerators
        if(this.denominator == other.denominator) {
            return new Fraction(this.numerator - other.numerator, this.denominator);
        }

        // Finding common denominator
        int calculatedNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int calculatedDenominator = this.denominator * other.denominator;

        return new Fraction(calculatedNumerator, calculatedDenominator);
    }

    public Fraction multiply(Fraction other) {
        int calculatedNumerator = this.numerator * other.numerator;
        int calculatedDenominator = this.denominator * other.denominator;

        return new Fraction(calculatedNumerator, calculatedDenominator);
    }

    public Fraction divide(Fraction other) {
        // Zero divide error
        if(other.numerator == 0) {
            System.out.println("Cannot be divided by zero! Return original fraction.");
            return new Fraction(this);
        }

        int calculatedNumerator = this.numerator * other.denominator;
        int calculatedDenominator = this.denominator * other.numerator;

        return new Fraction(calculatedNumerator, calculatedDenominator);
    }

    public void display() {
        System.out.println(numerator + "/" + denominator);
    }
}
