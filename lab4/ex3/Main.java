import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class Delivery {
  private static int deliveryCount = 0;
  private String type;
  private String orderId;
  private String customerName;
  private double distance;
  private double weight;

  public Delivery(String type, String orderId, String customerName, double distance, double weight) {
    this.type = type;
    this.orderId = orderId;
    this.customerName = customerName;
    this.distance = distance;
    this.weight = weight;
    deliveryCount++;
  }

  public String getType() { return type; }
  public String getOrderId() { return orderId; }
  public String getCustomerName() { return customerName; }
  public double getDistance() { return distance; }
  public double getWeight() { return weight; }
  public static int getDeliveryCount() { return deliveryCount; }

  public abstract double calculateShippingFee();

  public void displayInfo() {
    System.out.println("Type: " + getType());
    System.out.println("Order ID: " + getOrderId());
    System.out.println("Customer: " + getCustomerName());
    System.out.println("Distance: " + getDistance() + " km");
    System.out.println("Weight: " + getWeight() + " kg");
    System.out.printf("Shipping fee: %.2f VND%n", calculateShippingFee());
  }
}

class StandardDelivery extends Delivery {
  public StandardDelivery(String orderId, String customerName, double distance, double weight) {
    super("Standard", orderId, customerName, distance, weight);
  }

  @Override
  public double calculateShippingFee() {
    return 15_000 + 3_000 * getDistance() + 2_000 * getWeight();
  }
}

class ExpressDelivery extends Delivery {
  public ExpressDelivery(String orderId, String customerName, double distance, double weight) {
    super("Express", orderId, customerName, distance, weight);
  }

  @Override
  public double calculateShippingFee() {
    return 30_000 + 5_000 * getDistance() + 4_000 * getWeight();
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Delivery> deliveries = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addDeliveries(sc, deliveries);
          break;
        case 2:
          displayDeliveries(deliveries);
          break;
        case 3:
          searchDelivery(sc, deliveries);
          break;
        case 4:
          displayStatistics(sc, deliveries);
          break;
        case 0:
          System.out.println("Goodbye.");
          break;
        default:
          System.out.println("Invalid option. Choose 0 to 4.");
      }
    } while (choice != 0);

    sc.close();
  }

  private static void printMenu() {
    System.out.println("\n=== Online Order Delivery Management ===");
    System.out.println("1. Add deliveries");
    System.out.println("2. Display all deliveries");
    System.out.println("3. Search by order ID");
    System.out.println("4. Show statistics and deliveries over a fee threshold");
    System.out.println("0. Exit");
  }

  private static void addDeliveries(Scanner sc, List<Delivery> deliveries) {
    System.out.print("Enter number of deliveries: ");
    int n = Integer.parseInt(sc.nextLine());
    while (n < 0) {
      System.out.println("Invalid number - must be non-negative.");
      System.out.print("Enter number of deliveries: ");
      n = Integer.parseInt(sc.nextLine());
    }

    for (int i = 0; i < n; ++i) {
      System.out.println("\nDelivery #" + (i + 1) + ":");
      System.out.print("Enter type (Standard or Express): ");
      String type = sc.nextLine();
      if (!type.equalsIgnoreCase("Standard") && !type.equalsIgnoreCase("Express")) {
        System.out.println("Invalid type - must be Standard or Express.");
        i--;
        continue;
      }

      System.out.print("Enter order ID: ");
      String orderId = sc.nextLine();
      if (orderId.trim().isEmpty()) {
        System.out.println("Invalid order ID - must be non-empty.");
        i--;
        continue;
      }

      System.out.print("Enter customer name: ");
      String customerName = sc.nextLine();
      if (customerName.trim().isEmpty()) {
        System.out.println("Invalid customer name - must be non-empty.");
        i--;
        continue;
      }

      System.out.print("Enter distance in km: ");
      double distance = Double.parseDouble(sc.nextLine());
      System.out.print("Enter weight in kg: ");
      double weight = Double.parseDouble(sc.nextLine());
      if (distance <= 0 || weight <= 0) {
        System.out.println("Invalid distance and weight - both must be positive.");
        i--;
        continue;
      }

      if (type.equalsIgnoreCase("Standard")) {
        deliveries.add(new StandardDelivery(orderId, customerName, distance, weight));
      } else {
        deliveries.add(new ExpressDelivery(orderId, customerName, distance, weight));
      }
    }
  }

  private static void displayDeliveries(List<Delivery> deliveries) {
    if (deliveries.isEmpty()) {
      System.out.println("No deliveries available.");
      return;
    }

    for (int i = 0; i < deliveries.size(); ++i) {
      System.out.println("\nDelivery #" + (i + 1) + ":");
      deliveries.get(i).displayInfo();
    }
  }

  // Overload: display only deliveries whose fees exceed the given threshold.
  private static void displayDeliveries(List<Delivery> deliveries, double threshold) {
    int foundCount = 0;
    for (Delivery delivery : deliveries) {
      if (delivery.calculateShippingFee() > threshold) {
        delivery.displayInfo();
        foundCount++;
      }
    }
    if (foundCount == 0) {
      System.out.println("No deliveries exceed " + threshold + " VND.");
    }
  }

  private static void searchDelivery(Scanner sc, List<Delivery> deliveries) {
    System.out.print("Enter order ID: ");
    String orderId = sc.nextLine();
    for (Delivery delivery : deliveries) {
      if (delivery.getOrderId().equals(orderId)) {
        delivery.displayInfo();
        return;
      }
    }
    System.out.println("Order " + orderId + " not found.");
  }

  private static void displayStatistics(Scanner sc, List<Delivery> deliveries) {
    if (deliveries.isEmpty()) {
      System.out.println("No deliveries available.");
      return;
    }

    int standardCount = 0;
    int expressCount = 0;
    double totalRevenue = 0;
    for (Delivery delivery : deliveries) {
      if (delivery instanceof StandardDelivery) {
        standardCount++;
      } else if (delivery instanceof ExpressDelivery) {
        expressCount++;
      }
      totalRevenue += delivery.calculateShippingFee();
    }

    System.out.println("Standard deliveries: " + standardCount);
    System.out.println("Express deliveries: " + expressCount);
    System.out.printf("Total shipping revenue: %.2f VND%n", totalRevenue);
    System.out.println("Delivery objects created: " + Delivery.getDeliveryCount());

    System.out.print("Enter fee threshold (VND): ");
    double threshold = Double.parseDouble(sc.nextLine());
    System.out.println("Deliveries with fees over " + threshold + " VND:");
    displayDeliveries(deliveries, threshold);
  }
}
