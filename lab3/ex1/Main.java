import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// Base class
abstract class Student {
  private String studentId;
  private String fullName;
  private String address;
  private int totalCredits;
  private double gpa;

  public Student(String studentId, String fullName, String address, int totalCredits, double gpa) {
    this.studentId = studentId;
    this.fullName = fullName;
    this.address = address;
    this.totalCredits = totalCredits;
    this.gpa = gpa;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getFullName() {
    return fullName;
  }

  public String getAddress() {
    return address;
  }

  public double getGpa() {
    return gpa;
  }

  public int getTotalCredits() {
    return totalCredits;
  }

  public abstract boolean isEligibleForGraduation();

  public void displayInfo() {
    System.out.println("ID: " + studentId);
    System.out.println("Name: " + fullName);
    System.out.println("Address: " + address);
    System.out.println("Credits: " + totalCredits);
    System.out.println("GPA: " + gpa);
  }
}

// College student
class CollegeStudent extends Student {
  private double graduationExamScore;

  public CollegeStudent(String studentId, String fullName, String address, int totalCredits, double gpa, double examScore) {
    super(studentId, fullName, address, totalCredits, gpa);
    this.graduationExamScore = examScore;
  }

  @Override
  public boolean isEligibleForGraduation() {
    return getTotalCredits() >= 120
      && getGpa() >= 5.0
      && graduationExamScore >= 5.0;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Exam score: " + graduationExamScore);
  }
}

// University student
class UniversityStudent extends Student {
  private String thesisTitle;
  private double thesisScore;

  public UniversityStudent(String studentId, String fullName, String address, int totalCredits, double gpa, String thesisTitle, double thesisScore) {
    super(studentId, fullName, address, totalCredits, gpa);
    this.thesisTitle = thesisTitle;
    this.thesisScore = thesisScore;
  }

  @Override
  public boolean isEligibleForGraduation() {
    return getTotalCredits() >= 170
    && getGpa() >= 5.0
    && thesisScore >= 5.0;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Thesis: " + thesisTitle);
    System.out.println("Thesis score: " + thesisScore);
  }
}

// Main program
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Student> students = new ArrayList<>();
    
    System.out.println("Enter number of students: ");
    int n = Integer.parseInt(sc.nextLine());

    for(int i = 0; i < n; ++i) {
      System.out.println("\nStudent " + (i + 1));
      System.out.println("Type (1-College, 2-University): ");
      int type = Integer.parseInt(sc.nextLine());

      if(type != 1 && type != 2) {
        System.out.println("Invalid student type.");
        i--;
        continue;
      }

      System.out.println("Student ID: ");
      String id = sc.nextLine();

      System.out.println("Full name: ");
      String name = sc.nextLine();

      System.out.println("Address: ");
      String address = sc.nextLine();

      System.out.println("Total credits: ");
      int credits = Integer.parseInt(sc.nextLine());

      System.out.println("GPA: ");
      double gpa = Double.parseDouble(sc.nextLine());

      if(credits <= 0 || gpa < 0 || gpa > 10) {
        System.out.println("Invalid credits for GPA.");
        i--;
        continue;
      }

      if(type == 1) {
        System.out.println("Graduation exam score: ");
        double examScore = Double.parseDouble(sc.nextLine());

        if(examScore < 0 || examScore > 10) {
          System.out.println("Invalid exam score.");
          i--;
          continue;
        }

        students.add(new CollegeStudent(id, name, address, credits, gpa, examScore));
      } else {
        System.out.println("Thesis title: ");
        String thesisTitle = sc.nextLine();

        System.out.println("Thesis score: ");
        double thesisScore = Double.parseDouble(sc.nextLine());

        if(thesisScore < 0 || thesisScore > 10) {
          System.out.println("Invalid thesis score.");
          i--;
          continue;
        }

        students.add(new UniversityStudent(id, name, address, credits, gpa, thesisTitle, thesisScore));
      }
    }

    // Count eligible students
    int count = 0;
    for(Student s : students) {
      if(s.isEligibleForGraduation()) {
        count++;
      }
    }

    System.out.println("\nEligible students: " + count);

    // Find University with highest GPA
    UniversityStudent best = null;
    for(Student s : students) {
      if(s instanceof UniversityStudent) {
        UniversityStudent u = (UniversityStudent) s;
        
        if(best == null || u.getGpa() > best.getGpa()) {
          best = u;
        }
      }
    }

    if(best != null) {
      System.out.println("\nUniversity student with highest GPA:");
      best.displayInfo();
    } else {
      System.out.println("No university student found.");
    }

    sc.close();
  }
}