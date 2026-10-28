import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class Singer {
  private String fullName;
  private int yearsExperience;
  private int albumsSold;
  private int performances;

  public Singer(String fullName, int yearsExperience, int albumsSold, int performances) {
    this.fullName = fullName;
    this.yearsExperience = yearsExperience;
    this.albumsSold = albumsSold;
    this.performances = performances;
  }

  public String getFullName() {
    return fullName;
  }

  public int getYearsExperience() {
    return yearsExperience;
  }

  public int getAlbumsSold() {
    return albumsSold;
  }

  public int getPerformances() {
    return performances;
  }

  abstract public int calculateSalary();

  public void displayInfo() {
    System.out.println("Name: " + fullName);
    System.out.println("Years of experience: " + yearsExperience);
    System.out.println("Albums sold: " + albumsSold);
    System.out.println("Performances: " + performances);
    System.out.println("Salary: " + calculateSalary() + " VND");
  }
}

class RegularSinger extends Singer {
  public RegularSinger(String fullName, int yearsExperience, int albumsSold, int performances) {
    super(fullName, yearsExperience, albumsSold, performances);
  }

  @Override
  public int calculateSalary() {
    return 3_000_000 + 500_000*getYearsExperience() + 1_000*getAlbumsSold() + 200_000*getPerformances();
  }
}

class FamousSinger extends Singer {
  private int gameShows;

  public FamousSinger(String fullName, int yearsExperience, int albumsSold, int performances, int gameShows) {
    super(fullName, yearsExperience, albumsSold, performances);
    this.gameShows = gameShows;
  }

  public int getGameShows() {
    return gameShows;
  }

  @Override
  public int calculateSalary() {
    return 5_000_000 + 500_000*getYearsExperience() + 1_200*getAlbumsSold() + 500_000*getPerformances() + 500_000*getGameShows();
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Game shows: " + gameShows);
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Singer> singers = new ArrayList<>();

    System.out.print("Enter number of singers: ");
    int n = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < n; ++i) {
      System.out.println("\nSinger #" + (i + 1));
      System.out.print("Type (1-Regular, 2-Famous): ");
      int type = Integer.parseInt(sc.nextLine());
      if(type < 1 || 2 < type) {
        System.out.println("Type must be number 1 or 2.");
        i--;
        continue;
      }

      System.out.print("Full name: ");
      String fullName = sc.nextLine();

      System.out.print("Years of experience: ");
      int yearsExperience = Integer.parseInt(sc.nextLine());
      if(yearsExperience < 0) {
        System.out.println("Years of experience must be a non-negative integer number.");
        i--;
        continue;
      }

      System.out.print("Albums sold: ");
      int albumsSold = Integer.parseInt(sc.nextLine());
      if(albumsSold < 0) {
        System.out.println("Albums sold must be a non-negative integer number.");
        i--;
        continue;
      }

      System.out.print("Performances: ");
      int performances = Integer.parseInt(sc.nextLine());
      if(performances < 0) {
        System.out.println("Performances must be a non-negative integer number.");
        i--;
        continue;
      }

      if (type == 1) {
        singers.add(new RegularSinger(fullName, yearsExperience, albumsSold, performances));
      } else {
        System.out.print("Game shows attended: ");
        int gameShows = Integer.parseInt(sc.nextLine());
        if(gameShows < 0) {
          System.out.println("Game shows must be a non-negative integer number.");
          i--;
          continue;
        }
        singers.add(new FamousSinger(fullName, yearsExperience, albumsSold, performances, gameShows));
      }
    }

    System.out.println("\n===== SINGER SALARIES =====");
    Singer best = null;
    for(int i = 0; i < singers.size(); ++i) {
      Singer singer = singers.get(i);
      System.out.println("Singer: " + singer.getFullName() + ", Salary: " + singer.calculateSalary());
      if(best == null || best.calculateSalary() < singer.calculateSalary()) {
        best = singer;
      }
    }

    if (best != null) {
      System.out.println("\n===== HIGHEST-PAID SINGER =====");
      best.displayInfo();
    } else {
      System.out.println("No singer found.");
    }

    sc.close();
  }
}
