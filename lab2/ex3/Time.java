public class Time {
    // 1. Attributes
    private int hour, minute, second;

    // 2. No-argument constructor
    public Time() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    // 3. Parameterized constructor
    public Time(int hour, int minute, int second) {
        this.hour = 0 <= hour && hour <= 23 ? hour : 0;
        this.minute = 0 <= minute && minute <= 59 ? minute : 0;
        this.second = 0 <= second && second <= 59 ? second : 0;
    }

    public Time(Time other) {
        this.hour = other.hour;
        this.minute = other.minute;
        this.second = other.second;
    }

    public void display() {
        System.out.print(hour + ":" + minute + ":" + second);
    }

    public Time addSeconds(int seconds) {
        // Total time in second
        int total = hour * 3600 + minute * 60 + second + seconds;
        
        // Normalize to [00:00:00; 23:59:59] = [0, 86400] in seconds
        int normalize = ((total % 86400) + 86400) % 86400;

        int newHour = normalize / 3600;
        int newMinute = (normalize % 3600) / 60;
        int newSecond = normalize % 60;

        return new Time(newHour, newMinute, newSecond);
    }

    public Time subtractSeconds(int seconds) {
        return addSeconds(-seconds);
    }
}
