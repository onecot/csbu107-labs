import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

abstract class Employee {
  private String id;
  private String fullName;
  private String department;

  public Employee(String id, String fullName, String department) {
    this.id = id;
    this.fullName = fullName;
    this.department = department;
  }

  public String getId() { return this.id; }
  public String getFullName() { return this.fullName; }
  public String getDepartment() { return this.department; }

  public abstract double calculateSalary();

  public void displayInfo() {
    System.out.println("\n=== Employee Payroll Details ===");
    System.out.println("Employee ID: " + getId());
    System.out.println("Full name: " + getFullName());
    System.out.println("Department: " + getDepartment());
    System.out.printf("Monthly salary: %.2f VND%n", calculateSalary());
  }
}

class FullTimeEmployee extends Employee {
  private long baseSalary;
  private long allowance;
  private int workdays;

  public FullTimeEmployee(String id, String fullName, String department, long baseSalary, long allowance, int workdays) {
    super(id, fullName, department);
    this.baseSalary = baseSalary;
    this.allowance = allowance;
    this.workdays = workdays;
  }

  public long getBaseSalary() { return baseSalary; }
  public long getAllowance() { return allowance; }
  public int getWorkdays() { return workdays; }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Type: Full-time");
    System.out.println("Base salary: " + getBaseSalary() + " VND");
    System.out.println("Allowance: " + getAllowance() + " VND");
    System.out.println("Workdays: " + getWorkdays());
  }

  @Override
  public double calculateSalary() {
    return (double) getBaseSalary() * Math.min(getWorkdays(), 26) / 26 + getAllowance();
  }
}

class PartTimeEmployee extends Employee {
  private int hoursWorked;
  private long hourlyRate;

  public PartTimeEmployee(String id, String fullName, String department, int hoursWorked, long hourlyRate) {
    super(id, fullName, department);
    this.hoursWorked = hoursWorked;
    this.hourlyRate = hourlyRate;
  }

  public int getHoursWorked() { return hoursWorked; }
  public long getHourlyRate() { return hourlyRate; }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Type: Part-time");
    System.out.println("Hours worked: " + getHoursWorked());
    System.out.println("Hourly rate: " + getHourlyRate() + " VND");
  }

  @Override
  public double calculateSalary() {
    return getHoursWorked() * getHourlyRate();
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Employee> employees = new ArrayList<>();

    int choice;
    do {
      printMenu();
      System.out.print("Choose an option: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 1:
          addEmployees(sc, employees);
          break;
        case 2:
          displayPayroll(employees);
          break;
        case 3:
          searchEmployee(sc, employees);
          break;
        case 4:
          findEmployeesByDepartment(sc, employees);
          break;
        case 5:
          displayStatistics(employees);
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
    System.out.println("\n=== Employee Payroll Management ===");
    System.out.println("1. Add employees");
    System.out.println("2. Display payroll");
    System.out.println("3. Search employee by ID or name and department");
    System.out.println("4. Find employees by department");
    System.out.println("5. Show payroll statistics");
    System.out.println("0. Exit");
  }

  private static void addEmployees(Scanner sc, List<Employee> employees) {
    System.out.print("Enter number of employees: ");
    int n = Integer.parseInt(sc.nextLine());
    while (n < 0) {
      System.out.println("Invalid number - must be non-negative.");
      System.out.print("Enter number of employees: ");
      n = Integer.parseInt(sc.nextLine());
    }

    for(int i = 0; i < n; ++i) {
      System.out.println("\nEmployee #" + (i + 1) + ":");

      System.out.print("Enter type (Full-time or Part-time): ");
      String type = sc.nextLine();
      if(!type.equalsIgnoreCase("Full-time") && !type.equalsIgnoreCase("Part-time")) {
        System.out.println("Invalid type - must be Full-time or Part-time.");
        i--; continue;
      }

      System.out.print("Enter ID: ");
      String id = sc.nextLine();
      if(id.trim().isEmpty()) {
        System.out.println("Invalid ID - must be non-empty.");
        i--; continue;
      }
      System.out.print("Enter full name: ");
      String fullName = sc.nextLine();
      if(fullName.trim().isEmpty()) {
        System.out.println("Invalid name - must be non-empty.");
        i--; continue;
      }

      System.out.print("Enter department: ");
      String department = sc.nextLine();
      if(department.trim().isEmpty()) {
        System.out.println("Invalid department - must be non-empty.");
        i--; continue;
      }

      if(type.equalsIgnoreCase("Full-time")) {
        System.out.print("Enter base salary (VND): ");
        long baseSalary = Long.parseLong(sc.nextLine());
        System.out.print("Enter allowance (VND): ");
        long allowance = Long.parseLong(sc.nextLine());
        System.out.print("Enter workdays (0-31): ");
        int workdays = Integer.parseInt(sc.nextLine());

        if(baseSalary < 0 || allowance < 0 || workdays < 0 || workdays > 31) {
          System.out.println("Invalid values - salaries must be non-negative and workdays 0-31.");
          i--;
          continue;
        }
        employees.add(new FullTimeEmployee(id, fullName, department, baseSalary, allowance, workdays));
      } else {
        System.out.print("Enter hours worked: ");
        int hoursWorked = Integer.parseInt(sc.nextLine());
        System.out.print("Enter hourly rate (VND): ");
        long hourlyRate = Long.parseLong(sc.nextLine());

        if(hoursWorked < 0 || hourlyRate < 0) {
          System.out.println("Invalid values - hours and hourly rate must be non-negative.");
          i--;
          continue;
        }
        employees.add(new PartTimeEmployee(id, fullName, department, hoursWorked, hourlyRate));
      }
    }
  }

  private static void displayPayroll(List<Employee> employees) {
    if (employees.isEmpty()) {
      System.out.println("No employees available.");
      return;
    }

    for(int i = 0; i < employees.size(); ++i) {
      System.out.println("\nEmployee #" + (i + 1) + ":");
      employees.get(i).displayInfo();
    }
  }

  private static void searchEmployee(Scanner sc, List<Employee> employees) {
    System.out.println("1. Search by ID");
    System.out.println("2. Search by name and department");
    System.out.print("Choose a search option: ");
    int searchChoice = Integer.parseInt(sc.nextLine());

    Employee employee = null;
    if (searchChoice == 1) {
      System.out.print("Enter employee ID: ");
      String id = sc.nextLine();
      employee = findEmployee(employees, id);
    } else if (searchChoice == 2) {
      System.out.print("Enter full name: ");
      String name = sc.nextLine();
      System.out.print("Enter department: ");
      String department = sc.nextLine();
      employee = findEmployee(employees, name, department);
    } else {
      System.out.println("Invalid search option.");
      return;
    }

    if (employee == null) {
      System.out.println("Employee not found.");
    } else {
      employee.displayInfo();
    }
  }

  private static Employee findEmployee(List<Employee> employees, String id) {
    for (Employee employee : employees) {
      if (employee.getId().equals(id)) {
        return employee;
      }
    }
    return null;
  }

  private static Employee findEmployee(List<Employee> employees, String name, String department) {
    for (Employee employee : employees) {
      if (employee.getFullName().equalsIgnoreCase(name)
          && employee.getDepartment().equalsIgnoreCase(department)) {
        return employee;
      }
    }
    return null;
  }

  private static void findEmployeesByDepartment(Scanner sc, List<Employee> employees) {
    System.out.print("Enter department: ");
    String department = sc.nextLine();
    int foundCount = 0;

    for (Employee employee : employees) {
      if (employee.getDepartment().equalsIgnoreCase(department)) {
        employee.displayInfo();
        foundCount++;
      }
    }

    if (foundCount == 0) {
      System.out.println("No employees found in department " + department + ".");
    }
  }

  private static void displayStatistics(List<Employee> employees) {
    if (employees.isEmpty()) {
      System.out.println("No employees available.");
      return;
    }

    double totalPayroll = 0;
    Employee highestSalaryEmployee = employees.get(0);

    for (Employee employee : employees) {
      double salary = employee.calculateSalary();
      totalPayroll += salary;
      if (salary > highestSalaryEmployee.calculateSalary()) {
        highestSalaryEmployee = employee;
      }
    }

    System.out.printf("Total payroll: %.2f VND%n", totalPayroll);
    System.out.println("Employee with the highest salary:");
    highestSalaryEmployee.displayInfo();
  }
}
