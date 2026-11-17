class SmartCamera extends SmartDevice {
  private String resolution;
  private int abnormalDetectionCount;

  public SmartCamera() {
    super();
  }

  public SmartCamera(String deviceId, String deviceName, String installedArea, int operatingTime, double hourlyElectricityConsumption, String resolution, int abnormalDetectionCount) {
    super(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption);
    this.resolution = resolution;
    this.abnormalDetectionCount = abnormalDetectionCount;
  }

  public SmartCamera(SmartCamera other) {
    super(other);
    this.resolution = other.resolution;
    this.abnormalDetectionCount = other.abnormalDetectionCount;
  }

  public String getResolution() { return resolution; }

  private int getResolutionScore() {
    if (resolution.equals("HD")) return 10;
    if (resolution.equals("FullHD")) return 15;
    if (resolution.equals("2K")) return 20;
    if (resolution.equals("4K")) return 25;
    return 0;
  }

  public int getAbnormalDetectionCount() { return abnormalDetectionCount; }

  @Override
  public double calculateEfficiencyScore() {
    double score = 70 - getHourlyElectricityConsumption() * getOperatingTime() * 1.5 + getResolutionScore() + getAbnormalDetectionCount() * 2;
    
    if (score < 0) score = 0.0;
    else if (score > 100) score = 100.0;

    return score;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Camera resolution: " + getResolution());
    System.out.println("Abnormal detections: " + getAbnormalDetectionCount());
    System.out.println("Efficiency score: " + calculateEfficiencyScore());
  }
}