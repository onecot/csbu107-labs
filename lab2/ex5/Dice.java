public class Dice {
    // 1. Attributes
    private int value;
    private final int maxValue = 6, minValue = 1;

    // 2. Constructor
    public Dice() {
        this.value = 1;
    }

    public int roll() {
        int randomValue = (int)(Math.random() * (maxValue - minValue + 1)) + minValue;
        value = randomValue;
        return value;
    }
}
