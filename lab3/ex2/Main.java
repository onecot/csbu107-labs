import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Base class
abstract class Ticket {
  private String ticketId;
  private String holderName;
  private int yearOfBirth;
  private int numOfGames;

  public Ticket(String ticketId, String holderName, int yearOfBirth, int numOfGames) {
    this.ticketId = ticketId;
    this.holderName = holderName;
    this.yearOfBirth = yearOfBirth;
    this.numOfGames = numOfGames;
  }

  public String getTicketId() {
    return ticketId;
  }

  public String getHolderName() {
    return holderName;
  }

  public int getYearOfBirth() {
    return yearOfBirth;
  }

  public int getNumOfGames() {
    return numOfGames;
  }

  public abstract int getPrice();
}

// Full Package Ticket
class FullPackageTicket extends Ticket {
  public FullPackageTicket(String ticketId, String holderName, int yearOfBirth, int numOfGames) {
    super(ticketId, holderName, yearOfBirth, numOfGames);
  }

  @Override 
  public int getPrice() {
    return 200000;
  }
}

// Partial Ticket
class PartialTicket extends Ticket {
  public PartialTicket(String ticketId, String holderName, int yearOfBirth, int numOfGames) {
    super(ticketId, holderName, yearOfBirth, numOfGames);
  }

  @Override
  public int getPrice() {
    return 70000 + 20000 * getNumOfGames();
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Ticket> tickets = new ArrayList<>();

    // Input a list of tickets of both types
    System.out.print("Enter number of tickets: ");
    int n = Integer.parseInt(sc.nextLine());

    for(int i = 0; i < n; ++i) {
      System.out.println("\nTicket #" + (i + 1));
      System.out.print("Type (1-Full Package, 2-Partial): ");
      int type = Integer.parseInt(sc.nextLine());

      if(type != 1 && type != 2) {
        System.out.println("Invalid ticket type.");
        --i;
        continue;
      }

      System.out.print("Ticket ID: ");
      String ticketId = sc.nextLine();

      System.out.print("Holder name: ");
      String holderName = sc.nextLine();

      System.out.print("Year of birth: ");
      int yearOfBirth = Integer.parseInt(sc.nextLine());

      if(yearOfBirth < 1900 || yearOfBirth > 2026) {
        System.out.println("Invalid year of birth.");
        --i;
        continue;
      }

      if(type == 1) { // Full Package Ticket
        System.out.println("Condition: Access to all 30 games.");
        int numOfGames = 30;

        tickets.add(new FullPackageTicket(ticketId, holderName, yearOfBirth, numOfGames));
      } else { // Partial Ticket
        System.out.print("Number of games to play (total 30 games): ");
        int numOfGames = Integer.parseInt(sc.nextLine());

        if(numOfGames < 1 || numOfGames > 30) {
          System.out.println("Invalid number of games.");
          --i;
          continue;
        }

        System.out.println("Condition: Access to " + numOfGames + " games.");

        tickets.add(new PartialTicket(ticketId, holderName, yearOfBirth, numOfGames));
      }

      System.out.println("Ticket #" + (i + 1) + " (" + ticketId + ") " + "price: " + tickets.getLast().getPrice());
    }

    
    // Calculate total revenue from all tickets old
    // and
    // Count the number of Full Package Tickets & Partial Tickets sold
    int totalRevenue = 0;
    int numOfFullPackage = 0, numOfPartial = 0;
    for(int i = 0; i < n; ++i) {
      totalRevenue += tickets.get(i).getPrice();

      if(tickets.get(i) instanceof FullPackageTicket) {
        numOfFullPackage += 1;
      } else if(tickets.get(i) instanceof PartialTicket) {
        numOfPartial += 1;
      }
    }
    
    System.out.println("\n*******************\n");
    System.out.println("Total revenue: " + totalRevenue);
    System.out.println("Number of Full Package Tickets: " + numOfFullPackage);
    System.out.println("Number of Partial Tickets: " + numOfPartial);

    sc.close();
  }
}
