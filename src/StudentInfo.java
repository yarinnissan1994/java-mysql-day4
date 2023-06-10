public class StudentInfo {
    public StudentInfo(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public StudentInfo(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public int studentId;
    public String firstName;
    public String lastName;
    public String email;
}
