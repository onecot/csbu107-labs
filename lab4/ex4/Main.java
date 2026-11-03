import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class Ticket {
  private String type;
  private String id;
  private String movieTitle;
  private int quantity;

  public Ticket(String type, String id, String movieTitle, int quantity) {
    this.type = type;
    this.id = id;
    this.movieTitle = movieTitle;
    this.quantity = quantity;
  }

  public String getType() { return type; }
  public String getId() { return id; }
  public String getMovieTitle() { return movieTitle; }
  public int getQuantity() { return quantity; }

  public abstract long getUnitPrice();
  public abstract long calculateDiscount();
  public abstract long calculateTotalPrice();

  public void displayInfo() {
    System.out.println("Type: " + getType());
    System.out.println("Ticket ID: " + getId());
    System.out.println("Movie: " + getMovieTitle());
    System.out.println("Quantity: " + getQuantity());
    System.out.println("Unit price: " + getUnitPrice() + " VND");
    System.out.println("Discount: " + calculateDiscount() + " VND");
    System.out.println("Final total: " + calculateTotalPrice() + " VND");
  }
}

class RegularTicket extends Ticket {
  public RegularTicket(String id, String movieTitle) {
    this(id, movieTitle, 1);
  }

  public RegularTicket(String id, String movieTitle, int quantity) {
    super("Regular", id, movieTitle, quantity);
  }

  @Override
  public long getUnitPrice() {
    return 80_000;
  }

  @Override
  public long calculateDiscount() {
    return getQuantity() >= 5 ? getUnitPrice() * getQuantity() / 10 : 0;
  }

  @Override
  public long calculateTotalPrice() {
    return getUnitPrice() * getQuantity() - calculateDiscount();
  }
}

class VIPTicket extends Ticket {
  public VIPTicket(String id, String movieTitle, int quantity) {
    super("VIP", id, movieTitle, quantity);
  }

  @Override
  public long getUnitPrice() {
    return 150_000;
  }

  @Override
  public long calculateDiscount() {
    return getQuantity() >= 3 ? getUnitPrice() * getQuantity() * 15 / 100 : 0;
  }

  @Override
  public long calculateTotalPrice() {
    return getUnitPrice() * getQuantity() - calculateDiscount();
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Ticket> tickets = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addTickets(sc, tickets);
          break;
        case 2:
          displayTickets(tickets);
          break;
        case 3:
          searchTickets(sc, tickets);
          break;
        case 4:
          displayStatistics(tickets);
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
    System.out.println("\n=== Cinema Ticket Sales Management ===");
    System.out.println("1. Add ticket purchases");
    System.out.println("2. Display purchases (highest total first)");
    System.out.println("3. Search by movie title");
    System.out.println("4. Show revenue statistics");
    System.out.println("0. Exit");
  }

  private static void addTickets(Scanner sc, List<Ticket> tickets) {
    System.out.print("Enter number of purchases: ");
    int n = Integer.parseInt(sc.nextLine());
    while (n < 0) {
      System.out.println("Invalid number - must be non-negative.");
      System.out.print("Enter number of purchases: ");
      n = Integer.parseInt(sc.nextLine());
    }

    for (int i = 0; i < n; ++i) {
      System.out.println("\nPurchase #" + (i + 1) + ":");
      System.out.print("Enter type (Regular or VIP): ");
      String type = sc.nextLine();
      if (!type.equalsIgnoreCase("Regular") && !type.equalsIgnoreCase("VIP")) {
        System.out.println("Invalid type - must be Regular or VIP.");
        i--;
        continue;
      }

      System.out.print("Enter ticket ID: ");
      String id = sc.nextLine();
      if (id.trim().isEmpty()) {
        System.out.println("Invalid ID - must be non-empty.");
        i--;
        continue;
      }
      boolean isIdAvailable = true;
      for (Ticket ticket : tickets) {
        if (ticket.getId().equals(id)) {
          isIdAvailable = false;
          break;
        }
      }
      if (!isIdAvailable) {
        System.out.println("Invalid ID - already exists.");
        i--;
        continue;
      }

      System.out.print("Enter movie title: ");
      String movieTitle = sc.nextLine();
      if (movieTitle.trim().isEmpty()) {
        System.out.println("Invalid movie title - must be non-empty.");
        i--;
        continue;
      }

      System.out.print("Enter quantity: ");
      int quantity = Integer.parseInt(sc.nextLine());
      if (quantity < 1) {
        System.out.println("Invalid quantity - must be at least 1.");
        i--;
        continue;
      }

      if (type.equalsIgnoreCase("Regular")) {
        tickets.add(new RegularTicket(id, movieTitle, quantity));
      } else {
        tickets.add(new VIPTicket(id, movieTitle, quantity));
      }
    }
  }

  private static void displayTickets(List<Ticket> tickets) {
    if (tickets.isEmpty()) {
      System.out.println("No purchases available.");
      return;
    }

    List<Ticket> sortedTickets = new ArrayList<>(tickets);
    for (int i = 0; i < sortedTickets.size() - 1; ++i) {
      for (int j = 0; j < sortedTickets.size() - 1 - i; ++j) {
        if (sortedTickets.get(j).calculateTotalPrice()
            < sortedTickets.get(j + 1).calculateTotalPrice()) {
          Ticket temp = sortedTickets.get(j);
          sortedTickets.set(j, sortedTickets.get(j + 1));
          sortedTickets.set(j + 1, temp);
        }
      }
    }

    for (int i = 0; i < sortedTickets.size(); ++i) {
      System.out.println("\nPurchase #" + (i + 1) + ":");
      sortedTickets.get(i).displayInfo();
    }
  }

  private static void searchTickets(Scanner sc, List<Ticket> tickets) {
    System.out.print("Enter movie title: ");
    String movieTitle = sc.nextLine();
    int foundCount = 0;

    for (Ticket ticket : tickets) {
      if (ticket.getMovieTitle().equalsIgnoreCase(movieTitle)) {
        ticket.displayInfo();
        foundCount++;
      }
    }

    if (foundCount == 0) {
      System.out.println("No purchases found for " + movieTitle + ".");
    }
  }

  private static void displayStatistics(List<Ticket> tickets) {
    if (tickets.isEmpty()) {
      System.out.println("No purchases available.");
      return;
    }

    long totalRevenue = 0;
    String highestRevenueMovie = tickets.get(0).getMovieTitle();
    long highestMovieRevenue = -1;

    for (Ticket ticket : tickets) {
      totalRevenue += ticket.calculateTotalPrice();
      long movieRevenue = 0;
      for (Ticket other : tickets) {
        if (ticket.getMovieTitle().equalsIgnoreCase(other.getMovieTitle())) {
          movieRevenue += other.calculateTotalPrice();
        }
      }
      if (movieRevenue > highestMovieRevenue) {
        highestMovieRevenue = movieRevenue;
        highestRevenueMovie = ticket.getMovieTitle();
      }
    }

    System.out.println("Total ticket revenue: " + totalRevenue + " VND");
    System.out.println("Movie with the highest total revenue: " + highestRevenueMovie);
    System.out.println("Revenue for that movie: " + highestMovieRevenue + " VND");
  }
}
