import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class LibraryItem {
  private String type;
  private String id;
  private String title;
  private String author;
  private int borrowedDays;

  public LibraryItem(String type, String id, String title, String author, int borrowedDays) {
    this.type = type;
    this.id = id;
    this.title = title;
    this.author = author;
    if(borrowedDays < 0) borrowedDays = 0;
    this.borrowedDays = borrowedDays;
  }

  public String getType() { return type; }
  public String getId() { return id; }
  public String getTitle() { return title; }
  public String getAuthor() { return author; }
  public int getBorrowedDays() { return borrowedDays; }

  public abstract int calculateFee();

  public void displayInfo() {
    System.out.println("Type: " + getType());
    System.out.println("ID: " + getId());
    System.out.println("Title: " + getTitle());
    System.out.println("Author: " + getAuthor());
    System.out.println("Number of days borrowed: " + getBorrowedDays());
    System.out.println("Fee: " + calculateFee());
  }
}

class PrintedBook extends LibraryItem {
  private int pages;

  public PrintedBook(String id, String title, String author, int borrowedDays, int pages) {
    super("Printed Book", id, title, author, borrowedDays);
    if(pages < 0) pages = 0;
    this.pages = pages;
  }

  public int getPages() { return pages; }

  @Override
  public int calculateFee() {
    return 5_000 * getBorrowedDays() + 10 * getPages();
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Number of pages: " + getPages());
  }
}

class Magazine extends LibraryItem {
  private int issueNumber;

  public Magazine(String id, String title, String author, int borrowedDays) {
    this(id, title, author, borrowedDays, 1);
  }

  public Magazine(String id, String title, String author, int borrowedDays, int issueNumber) {
    super("Magazine", id, title, author, borrowedDays);
    if(issueNumber < 0) issueNumber = 0;
    this.issueNumber = issueNumber;
  }

  public int getIssueNumber() { return issueNumber; }

  @Override
  public int calculateFee() {
    return 3_000 * getBorrowedDays() + 2_000;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Issue number: " + getIssueNumber());
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<LibraryItem> materials = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addMaterials(sc, materials);
          break;
        case 2:
          displayMaterials(materials);
          break;
        case 3:
          searchMaterial(sc, materials);
          break;
        case 4:
          displayStatistics(materials);
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
    System.out.println("\n=== Library Borrowing Fee Management ===");
    System.out.println("1. Add items");
    System.out.println("2. Display all items");
    System.out.println("3. Search by item ID");
    System.out.println("4. Show statistics");
    System.out.println("0. Exit");
  }

  private static void addMaterials(Scanner sc, List<LibraryItem> materials) {
    System.out.print("Enter number of items: ");
    int n = Integer.parseInt(sc.nextLine());

    for(int i = 0; i < n; ++i) {
      System.out.println("\nItem #" + (i + 1) + ":");

      System.out.print("Enter type: ");
      String type = sc.nextLine();
      if(!type.equals("Printed Book") && !type.equals("Magazine")) {
        System.out.print("Invalid type - must be Printed Book or Magazine.");
        i--; continue;
      }

      System.out.print("Enter ID: ");
      String id = sc.nextLine();
      if(id.isEmpty()) {
        System.out.println("Invalid ID - must be non-empty.");
        i--; continue;
      }
      /* Check available ID */
      boolean isIdAvailable = true;
      for(int j = 0; j < materials.size(); ++j) {
        if(id.equals(materials.get(j).getId())) {
          isIdAvailable = false;
          break;
        }
      }
      if(!isIdAvailable) {
        System.out.println("Invalid ID - already exists.");
        i--; continue;
      }

      System.out.print("Enter title: ");
      String title = sc.nextLine();
      if(title.isEmpty()) {
        System.out.println("Invalid title - must be non-empty.");
        i--; continue;
      }

      System.out.print("Enter author: ");
      String author = sc.nextLine();
      if(author.isEmpty()) {
        System.out.println("Invalid author - must be non-empty.");
        i--; continue;
      }

      System.out.print("Enter number of days borrowed: ");
      int borrowedDays = Integer.parseInt(sc.nextLine());
      if(borrowedDays < 1) {
        System.out.println("Invalid number of days borrowed - must be positive.");
        i--; continue;
      }

      if(type.equals("Printed Book")) {
        System.out.print("Enter number of pages: ");
        int pages = Integer.parseInt(sc.nextLine());
        if(pages < 1) {
          System.out.println("Invalid number of pages - must be positive.");
          i--;
          continue;
        }

        materials.add(new PrintedBook(id, title, author, borrowedDays, pages));
      } else {
        System.out.print("Enter issue number: ");
        int issueNumber = Integer.parseInt(sc.nextLine());
        if(issueNumber < 1) {
          System.out.println("Invalid issue number - must be positive.");
          i--;
          continue;
        }

        materials.add(new Magazine(id, title, author, borrowedDays, issueNumber));
      }
    }
  }

  private static void displayMaterials(List<LibraryItem> materials) {
    for(int i = 0; i < materials.size(); ++i) {
      System.out.println("\nItem #" + (i + 1) + ":");
      materials.get(i).displayInfo();
    }
  }

  private static void searchMaterial(Scanner sc, List<LibraryItem> materials) {
    String id;
    while(true) {
      System.out.print("Enter item ID: ");
      id = sc.nextLine();
      if(!id.isEmpty()) break;

      System.out.println("Invalid ID - must be non-empty.");
    }

    boolean isFound = false;
    for(int i = 0; i < materials.size(); ++i) {
      if(materials.get(i).getId().equals(id)) {
        materials.get(i).displayInfo();
        isFound = true;
        break;
      }
    }

    if(!isFound) {
      System.out.println("Item " + id + " not found.");
    }
  }

  private static void displayStatistics(List<LibraryItem> materials) {
    if (materials.isEmpty()) {
      System.out.println("No items available.");
      return;
    }

    int printedBookCount = 0;
    int magazineCount = 0;
    long totalRevenue = 0;
    LibraryItem highestFeeItem = materials.get(0);

    for (LibraryItem item : materials) {
      if (item instanceof PrintedBook) {
        printedBookCount++;
      } else if (item instanceof Magazine) {
        magazineCount++;
      }

      int fee = item.calculateFee();
      totalRevenue += fee;
      if (fee > highestFeeItem.calculateFee()) {
        highestFeeItem = item;
      }
    }

    System.out.println("Printed books: " + printedBookCount);
    System.out.println("Magazines: " + magazineCount);
    System.out.println("Total borrowing revenue: " + totalRevenue + " VND");
    System.out.println("Item with the highest fee:");
    System.out.println("  ID: " + highestFeeItem.getId());
    System.out.println("  Title: " + highestFeeItem.getTitle());
    System.out.println("  Fee: " + highestFeeItem.calculateFee() + " VND");
  }
}
