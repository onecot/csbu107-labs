import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class for all people
abstract class Person {
  private String fullName;
  private int age;

  public Person(String fullName, int age) {
    this.fullName = fullName;
    if(age <= 0) age = 1; // Age must be positive
    this.age = age;
  }

  public String getFullName() {
    return fullName;
  }

  public int getAge() {
    return age;
  }

  public abstract void displayInfo();
}

// Guest: has a citizen ID (CCCD)
class Guest extends Person {
  private String citizenId;

  public Guest(String fullName, int age, String citizenId) {
    super(fullName, age);
    this.citizenId = citizenId;
  }

  @Override
  public void displayInfo() {
    System.out.println("Guest: " + getFullName() + ", Age: " + getAge() + ", Citizen ID: " + citizenId);
  }

  public String getCitizenId() {
    return citizenId;
  }
}

// Staff: has a license ID and an employee ID
class Staff extends Person {
  private String licenseId;
  private String employeeId;

  public Staff(String fullName, int age, String licenseId, String employeeId) {
    super(fullName, age);
    this.licenseId = licenseId;
    this.employeeId = employeeId;
  }

  @Override
  public void displayInfo() {
    System.out.println("Staff: " + getFullName() + ", Age: " + getAge() + ", License ID: " + licenseId + ", Employee ID: " + employeeId);
  }
}

// Base class for rooms; subclasses provide the type and daily price
abstract class Room {
  public abstract String getRoomType();
  public abstract int getPricePerDay();

  // Rental cost = daily price * number of days
  public int calculateCost(int days) {
    if(days <= 0) days = 0;
    return getPricePerDay() * days;
  }
}

// VIP1: 900,000 VND per day
class RoomVIP1 extends Room {
  @Override
  public String getRoomType() {
    return "VIP1";
  }

  @Override
  public int getPricePerDay() {
    return 900000;
  }
}

// VIP2: 700,000 VND per day
class RoomVIP2 extends Room {
  @Override
  public String getRoomType() {
    return "VIP2";
  }

  @Override
  public int getPricePerDay() {
    return 700000;
  }
}

// VIP3: 500,000 VND per day
class RoomVIP3 extends Room {
  @Override
  public String getRoomType() {
    return "VIP3";
  }

  @Override
  public int getPricePerDay() {
    return 500000;
  }
}

// Main program
public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    List<Guest> guests = new ArrayList<>();

    // Menu loop
    while (true) {
      System.out.println("\n===== HOTEL MANAGEMENT =====");
      System.out.println("1. Add guest");
      System.out.println("2. Remove guest");
      System.out.println("3. List guests");
      System.out.println("4. Calculate rental cost");
      System.out.println("0. Exit");
      System.out.print("Choose: ");

      int choice = Integer.parseInt(sc.nextLine());

      if (choice == 0) break;

      switch (choice) {
        case 1:
          addGuest(guests);
          break;
        case 2:
          removeGuest(guests);
          break;
        case 3:
          listGuests(guests);
          break;
        case 4:
          calculateRentalCost();
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }

    sc.close();
  }

  // Read guest information and add it to the list
  static void addGuest(List<Guest> guests) {
    System.out.print("Full name: ");
    String fullName = sc.nextLine();

    System.out.print("Age: ");
    int age = Integer.parseInt(sc.nextLine());

    System.out.print("Citizen ID (CCCD): ");
    String citizenId = sc.nextLine();

    guests.add(new Guest(fullName, age, citizenId));
    System.out.println("Guest added.");
  }

  // Remove a guest by citizen ID
  static void removeGuest(List<Guest> guests) {
    System.out.print("Citizen ID to remove: ");
    String citizenId = sc.nextLine();

    boolean isFound = false;
    for(int i = 0; i < guests.size(); ++i) {
      if(citizenId.equals(guests.get(i).getCitizenId())) {
        isFound = true;
        guests.remove(i);
        break;
      }
    }

    if(!isFound) {
      System.out.println("Guest not found.");
      return;
    }

    System.out.println("Guest removed.");
  }

  // Print all guests
  static void listGuests(List<Guest> guests) {
    if (guests.isEmpty()) {
      System.out.println("No guests.");
      return;
    }

    for (Guest g : guests) {
      g.displayInfo();
    }
  }

  // Read room type and number of days, then print the rental cost
  static void calculateRentalCost() {
    System.out.print("Room type (1-VIP1, 2-VIP2, 3-VIP3): ");
    int type = Integer.parseInt(sc.nextLine());

    Room room = createRoom(type);
    if (room == null) {
      System.out.println("Invalid room type.");
      return;
    }

    System.out.print("Number of days: ");
    int days = Integer.parseInt(sc.nextLine());

    System.out.println("Room " + room.getRoomType() + " for " + days + " days: "
      + room.calculateCost(days) + " VND");
  }

  // Map the menu number to a room object
  static Room createRoom(int type) {
    switch (type) {
      case 1:
        return new RoomVIP1();
      case 2:
        return new RoomVIP2();
      case 3:
        return new RoomVIP3();
      default:
        return null;
    }
  }
}
