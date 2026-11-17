class SmartAC extends SmartDevice {
  private int averageTemperature;
  private int energySavingHours;

  public SmartAC() {
    super();
  }

  public SmartAC(String deviceId, String deviceName, String installedArea, int operatingTime, double hourlyElectricityConsumption, int averageTemperature, int energySavingHours) {
    super(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption);
    this.averageTemperature = averageTemperature;
    this.energySavingHours = energySavingHours;
  }

  public SmartAC(SmartAC other) {
    super(other);
    this.averageTemperature = other.averageTemperature;
    this.energySavingHours = other.energySavingHours;
  }

  public int getAveraveTemperature() { return averageTemperature; }
  public int getEnergySavingHours() { return energySavingHours; }

  @Override
  public double calculateEfficiencyScore() {
    double score = 100 - getHourlyElectricityConsumption() * getOperatingTime() * 3 + getEnergySavingHours() * 2 - Math.abs(averageTemperature - 26) * 1.5;
    
    if (score < 0) score = 0.0;
    else if (score > 100) score = 100.0;

    return score;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Average set temperature: " + getAveraveTemperature());
    System.out.println("Energy-saving hours: " + getEnergySavingHours());
    System.out.println("Efficiency score: " + calculateEfficiencyScore());
  }
}