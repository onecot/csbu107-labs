import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<SmartDevice> devices = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addDevices(sc, devices);           // 35 points - done
          break;
        case 2:
          displayDevices(devices);           // 10 points - done
          break;
        case 3:
          classifyDevices(devices);          // 20 points - done
          break;
        case 4:
          findHighestScoreDevices(devices);  // 10 points - done
          break;
        case 5:
          searchByArea(sc, devices);         // 10 points - done
          break;
        case 0:
          System.out.println("Goodbye.");
          break;
        default:
          System.out.println("Invalid option. Choose 0 to 5.");
      }
    } while (choice != 0);

    sc.close();
  }

  private static void printMenu() {
    System.out.println("\n=== Smart Campus ===");
    System.out.println("1. Enter a list of smart devices");
    System.out.println("2. Display detailed information");
    System.out.println("3. Classify devices by daily operating time");
    System.out.println("4. Find the highest efficiency score device in each type");
    System.out.println("5. Search by installation-area keyword (case-sensitive)");
    System.out.println("0. Exit");
  }

  private static void addDevices(Scanner sc, List<SmartDevice> devices) {
    System.out.print("\nEnter number of devices: ");
    int numberOfDevices = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < numberOfDevices; i++) {
      System.out.println("\nEnter device " + (i + 1) + ":");
      System.out.print("Device type: ");
      int deviceType = Integer.parseInt(sc.nextLine());

      System.out.print("Device ID: ");
      String deviceId = sc.nextLine();

      System.out.print("Device name: ");
      String deviceName = sc.nextLine();

      System.out.print("Installation area: ");
      String installedArea = sc.nextLine();

      System.out.print("Daily operating time: ");
      int operatingTime = Integer.parseInt(sc.nextLine());

      System.out.print("Hourly electricity use: ");
      double hourlyElectricityConsumption = Double.parseDouble(sc.nextLine());

      if (deviceType == 1) {
        
        /* Smart Light */
        System.out.print("Automatic brightness adjustments: ");
        int brightnessAdjustmentCount = Integer.parseInt(sc.nextLine());

        devices.add(new SmartLight(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption, brightnessAdjustmentCount));

      } else if (deviceType == 2) {
        
        /* Smart AC */
        System.out.print("Average set temperature: ");
        int averageTemperature = Integer.parseInt(sc.nextLine());
        
        System.out.print("Hours in energy-saving mode: ");
        int energySavingHours = Integer.parseInt(sc.nextLine());

        devices.add(new SmartAC(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption, averageTemperature, energySavingHours));

      } else if (deviceType == 3) {
        
        /* Smart Camera */
        System.out.print("Camera resolution: ");
        String resolution = sc.nextLine();

        System.out.print("Abnormal detections per day: ");
        int abnormalDetectionCount = Integer.parseInt(sc.nextLine());

        devices.add(new SmartCamera(deviceId, deviceName, installedArea, operatingTime, hourlyElectricityConsumption, resolution, abnormalDetectionCount));

      }
    }
  }
  
  private static void displayDevices(List<SmartDevice> devices) {
    System.out.println("LIST OF SMART DEVICES");

    for (int i = 0; i < devices.size(); i++) {
      System.out.print("\n" + (i + 1) + ". ");
      if (devices.get(i) instanceof SmartLight) System.out.println("SmartLight");
      else if (devices.get(i) instanceof SmartAC) System.out.println("SmartAC");
      else if (devices.get(i) instanceof SmartCamera) System.out.println("SmartCamera");
      
      devices.get(i).displayInfo();
    }
  }
  
  private static void classifyDevices(List<SmartDevice> devices) {
    System.out.println("\nDEVICES BY DAILY OPERATING TIME");

    System.out.println("\nLow usage (< 6 hours/day):");
    for (int i = 0; i < devices.size(); i++) {
      SmartDevice device = devices.get(i);
      if (device.getOperatingTime() < 6) {
        System.out.println(device.getDeviceId() + " - " + device.getDeviceName());
      }
    }

    System.out.println("\nMedium usage (6 to < 12 hours/day):");
    for (int i = 0; i < devices.size(); i++) {
      SmartDevice device = devices.get(i);
      if (device.getOperatingTime() >= 6 && device.getOperatingTime() < 12) {
        System.out.println(device.getDeviceId() + " - " + device.getDeviceName());
      }
    }

    System.out.println("\nHigh usage (12+ hours/day):");
    for (int i = 0; i < devices.size(); i++) {
      SmartDevice device = devices.get(i);
      if (device.getOperatingTime() >= 12) {
        System.out.println(device.getDeviceId() + " - " + device.getDeviceName());
      }
    }
  }

  private static void findHighestScoreDevices(List<SmartDevice> devices) {
    SmartDevice highestLight = null;
    SmartDevice highestAC = null;
    SmartDevice highestCamera = null;

    for (int i = 0; i < devices.size(); i++) {
      SmartDevice device = devices.get(i);

      if (device instanceof SmartLight
          && (highestLight == null
              || device.calculateEfficiencyScore() > highestLight.calculateEfficiencyScore())) {
        highestLight = device;
      } else if (device instanceof SmartAC
          && (highestAC == null
              || device.calculateEfficiencyScore() > highestAC.calculateEfficiencyScore())) {
        highestAC = device;
      } else if (device instanceof SmartCamera
          && (highestCamera == null
              || device.calculateEfficiencyScore() > highestCamera.calculateEfficiencyScore())) {
        highestCamera = device;
      }
    }

    System.out.println("\nHIGHEST EFFICIENCY SCORE BY DEVICE TYPE");
    if (highestLight != null) {
      System.out.println("SmartLight: " + highestLight.getDeviceId()
          + " - " + highestLight.getDeviceName()
          + " (" + highestLight.calculateEfficiencyScore() + ")");
    }
    if (highestAC != null) {
      System.out.println("SmartAC: " + highestAC.getDeviceId()
          + " - " + highestAC.getDeviceName()
          + " (" + highestAC.calculateEfficiencyScore() + ")");
    }
    if (highestCamera != null) {
      System.out.println("SmartCamera: " + highestCamera.getDeviceId()
          + " - " + highestCamera.getDeviceName()
          + " (" + highestCamera.calculateEfficiencyScore() + ")");
    }
  }

  private static void searchByArea(Scanner sc, List<SmartDevice> devices) {
    System.out.print("\nEnter installation-area keyword: ");
    String keyword = sc.nextLine();

    System.out.println("DEVICES FOUND:");
    for (int i = 0; i < devices.size(); i++) {
      SmartDevice device = devices.get(i);
      if (device.getInstalledArea().contains(keyword)) {
        System.out.println(device.getDeviceId() + " - " + device.getDeviceName()
            + " (" + device.getInstalledArea() + ")");
      }
    }
  }
}
