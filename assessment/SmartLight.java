class SmartLight extends SmartDevice {
  private int brightnessAdjustmentCount;

  public SmartLight() {
    super();
  }

  public SmartLight(String deviceId, String deviceName, String installedArea, int operatingTime, double hourlyElectricityConsumption, int brightnessAdjustmentCount) {
    super(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption);
    this.brightnessAdjustmentCount = brightnessAdjustmentCount;
  }

  public SmartLight(SmartLight other) {
    super(other);
    this.brightnessAdjustmentCount = other.brightnessAdjustmentCount;
  }

  public int getBrightnessAdjustmentCount() { return brightnessAdjustmentCount; }

  @Override
  public double calculateEfficiencyScore() {
    double score = 100 - getHourlyElectricityConsumption() * getOperatingTime() * 2 + getBrightnessAdjustmentCount() * 1.5;

    if (score < 0) score = 0.0;
    else if (score > 100) score = 100.0;

    return score;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Automatic brightness adjustments: " + getBrightnessAdjustmentCount());
    System.out.println("Efficiency score: " + calculateEfficiencyScore());
  }
}