abstract class SmartDevice {
  private String deviceId;
  private String deviceName;
  private String installedArea;
  private int operatingTime;                    // hours/day
  private double hourlyElectricityConsumption;  // kWh/hour

  public SmartDevice() {
  }

  public SmartDevice(String deviceId, String deviceName, String installedArea, int operatingTime, double hourlyElectricityConsumption) {
    this.deviceId = deviceId;
    this.deviceName = deviceName;
    this.installedArea = installedArea;
    this.operatingTime = operatingTime;
    this.hourlyElectricityConsumption = hourlyElectricityConsumption;
  }

  public SmartDevice(SmartDevice other) {
    this(other.deviceId, other.deviceName, other.installedArea, other.operatingTime,
        other.hourlyElectricityConsumption);
  }

  public String getDeviceId() { return deviceId; }
  public String getDeviceName() { return deviceName; }
  public String getInstalledArea() { return installedArea; }
  public int getOperatingTime() { return operatingTime; }
  public double getHourlyElectricityConsumption() { return hourlyElectricityConsumption; }

  public abstract double calculateEfficiencyScore();
  public void displayInfo() {
    System.out.println("Device ID: " + getDeviceId());
    System.out.println("Device name: " + getDeviceName());
    System.out.println("Installation area: " + getInstalledArea());
    System.out.println("Operating time: " + getOperatingTime() + " hours/day");
    System.out.println("Hourly electricity use: " + getHourlyElectricityConsumption() + " kWh/hour");
  }
}