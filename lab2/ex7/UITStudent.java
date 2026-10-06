public class UITStudent {
    // Attributes
    private String studentId, fullName, major;
    private double GPA;

    // No-argument constructor
    public UITStudent() {
        this.studentId = "Unknown";
        this.fullName = "Unknown";
        this.major = "Unknown";
        this.GPA = 0.0;
    }

    // Constructor with studentId & fullName
    public UITStudent(String studentId, String fullName) {
        this();
        this.studentId = studentId;
        this.fullName = fullName;
    }

    // Constructor with full information
    public UITStudent(String studentId, String fullName, String major, double GPA) {
        this(studentId, fullName);
        this.major = major;
        this.GPA = GPA;
        if(this.GPA < 0 || 10 < this.GPA) {
            System.out.print("GPA should be in [0;10].");
            this.GPA = 0.0;
        }
    }

    public void displayInfo() {
        System.out.println(studentId + " | " + fullName + " | " + major + " | " + GPA + " (" + academicClassification() + ")");
    }

    public boolean isPassed() {
        return GPA >= 5.0;
    }

    public String academicClassification() {
        if(GPA < 5.0) return "Fail";
        else if(GPA < 7.0) return "Average";
        else if(GPA < 8.0) return "Good";
        else if(GPA < 9.0) return "Very Good";
        return "Excellent"; // 
    }
}
