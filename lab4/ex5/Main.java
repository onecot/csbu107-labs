import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class CourseRegistration {
  private String type;
  private String code;
  private String courseName;
  private int credits;
  private int registeredStudents;

  public CourseRegistration(String type, String code, String courseName, int credits, int registeredStudents) {
    this.type = type;
    this.code = code;
    this.courseName = courseName;
    this.credits = credits;
    this.registeredStudents = registeredStudents;
  }

  public String getType() { return type; }
  public String getCode() { return code; }
  public String getCourseName() { return courseName; }
  public int getCredits() { return credits; }
  public int getRegisteredStudents() { return registeredStudents; }

  public abstract long tuitionPerStudent();

  public long expectedCollection() {
    return tuitionPerStudent() * getRegisteredStudents();
  }

  public void displayInfo() {
    System.out.println("Type: " + getType());
    System.out.println("Course code: " + getCode());
    System.out.println("Course name: " + getCourseName());
    System.out.println("Credits: " + getCredits());
    System.out.println("Registered students: " + getRegisteredStudents());
    System.out.println("Tuition per student: " + tuitionPerStudent() + " VND");
    System.out.println("Expected total collection: " + expectedCollection() + " VND");
  }
}

class TheoryCourse extends CourseRegistration {
  public TheoryCourse(String code, String courseName, int credits) {
    this(code, courseName, credits, 0);
  }

  public TheoryCourse(String code, String courseName, int credits, int registeredStudents) {
    super("Theory", code, courseName, credits, registeredStudents);
  }

  @Override
  public long tuitionPerStudent() {
    return getCredits() * 450_000L;
  }
}

class LabCourse extends CourseRegistration {
  private long equipmentFee;

  public LabCourse(String code, String courseName, int credits, int registeredStudents, long equipmentFee) {
    super("Laboratory", code, courseName, credits, registeredStudents);
    this.equipmentFee = equipmentFee;
  }

  public long getEquipmentFee() { return equipmentFee; }

  @Override
  public long tuitionPerStudent() {
    return getCredits() * 600_000L + getEquipmentFee();
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Equipment fee per student: " + getEquipmentFee() + " VND");
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<CourseRegistration> courses = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addCourses(sc, courses);
          break;
        case 2:
          displayCourses(courses);
          break;
        case 3:
          searchCourse(sc, courses);
          break;
        case 4:
          displayStatistics(courses);
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
    System.out.println("\n=== Course Registration and Tuition Management ===");
    System.out.println("1. Add courses");
    System.out.println("2. Display all courses");
    System.out.println("3. Search by course code");
    System.out.println("4. Show tuition statistics");
    System.out.println("0. Exit");
  }

  private static void addCourses(Scanner sc, List<CourseRegistration> courses) {
    System.out.print("Enter number of courses: ");
    int n = Integer.parseInt(sc.nextLine());
    while (n < 0) {
      System.out.println("Invalid number - must be non-negative.");
      System.out.print("Enter number of courses: ");
      n = Integer.parseInt(sc.nextLine());
    }

    for (int i = 0; i < n; ++i) {
      System.out.println("\nCourse #" + (i + 1) + ":");
      System.out.print("Enter type (Theory or Laboratory): ");
      String type = sc.nextLine();
      if (!type.equalsIgnoreCase("Theory") && !type.equalsIgnoreCase("Laboratory")) {
        System.out.println("Invalid type - must be Theory or Laboratory.");
        i--;
        continue;
      }

      System.out.print("Enter course code: ");
      String code = sc.nextLine();
      if (code.trim().isEmpty()) {
        System.out.println("Invalid course code - must be non-empty.");
        i--;
        continue;
      }
      boolean isCodeAvailable = true;
      for (CourseRegistration course : courses) {
        if (course.getCode().equals(code)) {
          isCodeAvailable = false;
          break;
        }
      }
      if (!isCodeAvailable) {
        System.out.println("Invalid course code - already exists.");
        i--;
        continue;
      }

      System.out.print("Enter course name: ");
      String courseName = sc.nextLine();
      if (courseName.trim().isEmpty()) {
        System.out.println("Invalid course name - must be non-empty.");
        i--;
        continue;
      }

      System.out.print("Enter number of credits: ");
      int credits = Integer.parseInt(sc.nextLine());
      System.out.print("Enter registered students: ");
      int registeredStudents = Integer.parseInt(sc.nextLine());
      if (credits < 1 || registeredStudents < 0) {
        System.out.println("Invalid values - credits must be at least 1 and students non-negative.");
        i--;
        continue;
      }

      if (type.equalsIgnoreCase("Theory")) {
        courses.add(new TheoryCourse(code, courseName, credits, registeredStudents));
      } else {
        System.out.print("Enter equipment fee per student (VND): ");
        long equipmentFee = Long.parseLong(sc.nextLine());
        if (equipmentFee < 0) {
          System.out.println("Invalid equipment fee - must be non-negative.");
          i--;
          continue;
        }
        courses.add(new LabCourse(code, courseName, credits, registeredStudents, equipmentFee));
      }
    }
  }

  private static void displayCourses(List<CourseRegistration> courses) {
    if (courses.isEmpty()) {
      System.out.println("No courses available.");
      return;
    }

    for (int i = 0; i < courses.size(); ++i) {
      System.out.println("\nCourse #" + (i + 1) + ":");
      courses.get(i).displayInfo();
    }
  }

  private static void searchCourse(Scanner sc, List<CourseRegistration> courses) {
    System.out.print("Enter course code: ");
    String code = sc.nextLine();
    for (CourseRegistration course : courses) {
      if (course.getCode().equals(code)) {
        course.displayInfo();
        return;
      }
    }
    System.out.println("Course " + code + " not found.");
  }

  private static void displayStatistics(List<CourseRegistration> courses) {
    int theoryCount = 0;
    int labCount = 0;
    int totalStudents = 0;
    long totalCollection = 0;
    double weightedTuitionTotal = 0;
    CourseRegistration highestCollectionCourse = null;

    for (CourseRegistration course : courses) {
      if (course instanceof TheoryCourse) {
        theoryCount++;
      } else if (course instanceof LabCourse) {
        labCount++;
      }

      totalStudents += course.getRegisteredStudents();
      totalCollection += course.expectedCollection();
      weightedTuitionTotal += (double) course.tuitionPerStudent() * course.getRegisteredStudents();
      if (highestCollectionCourse == null
          || course.expectedCollection() > highestCollectionCourse.expectedCollection()) {
        highestCollectionCourse = course;
      }
    }

    System.out.println("Theory courses: " + theoryCount);
    System.out.println("Laboratory courses: " + labCount);
    System.out.println("Total expected collection: " + totalCollection + " VND");
    if (highestCollectionCourse == null) {
      System.out.println("No courses available to compare.");
    } else {
      System.out.println("Course with the largest expected collection:");
      highestCollectionCourse.displayInfo();
    }

    if (totalStudents == 0) {
      System.out.println("Average tuition per student: N/A");
    } else {
      System.out.printf("Average tuition per student: %.2f VND%n", weightedTuitionTotal / totalStudents);
    }
  }
}
