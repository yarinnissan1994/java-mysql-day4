import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "root";
        String password = "password";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet result;

//           ***** Q1 *****
            result = statement.executeQuery("select first_name, last_name, email from students where date_of_birth like \"1994%\";");
            List<StudentInfo> bornBefore1994 = new ArrayList<>();
            while(result.next()){
                bornBefore1994.add(new StudentInfo(result.getString(1), result.getString(2), result.getString(3)));
            }
            System.out.println("Students who born before 1994:");
            for (StudentInfo student : bornBefore1994){
                System.out.println(student.firstName+" "+student.lastName+" | "+student.email);
            }

            System.out.println();

//            ***** Q2 *****
            result = statement.executeQuery("select course_name, count(student_id) as students from courses as c left join studentcourse as s on c.course_id = s.course_id group by c.course_id;");
            List<CourseStudentCount> coursesStudentCount = new ArrayList<>();
            while(result.next()){
                coursesStudentCount.add(new CourseStudentCount(result.getString(1), result.getInt(2)));
            }
            System.out.println("Courses students count:");
            for (CourseStudentCount course : coursesStudentCount){
                System.out.println(course.courseName+" | Student enrolled: "+course.studentCount);
            }

            System.out.println();
            
//            ***** Q3 *****
            result = statement.executeQuery("select first_name, last_name, course_name from students as s inner join studentcourse as sc on s.student_id = sc.student_id inner join courses as c on c.course_id = sc.course_id where course_name like \"Computer Science\";");
            List<StudentCourseInfo> computerScienceStudents = new ArrayList<>();
            while(result.next()){
                computerScienceStudents.add(new StudentCourseInfo(result.getString(1), result.getString(2), result.getString(3)));
            }
            System.out.println("Computer science students info:");
            for (StudentCourseInfo student : computerScienceStudents){
                System.out.println(student.firstName+" "+student.lastName+" | "+student.course);
            }

            System.out.println();

//            ***** Q4 *****
            result = statement.executeQuery("select email from students where first_name like \"J%\" and last_name like \"D%\";");
            List<Email> J_D_Emails = new ArrayList<>();
            while(result.next()){
                System.out.println(result.getString(1));
                J_D_Emails.add(new Email(result.getString(1)));
            }
            System.out.println("Email list of students (J____ D____):");
            for (Email email : J_D_Emails){
                System.out.println(email.email);
            }

            System.out.println();

//            ***** Q5 *****
            result = statement.executeQuery("select course_name, instructor_name from courses where instructor_name like \"%Brown\";");
            List<Course> profBrownCourses = new ArrayList<>();
            while(result.next()){
                profBrownCourses.add(new Course(result.getString(1), result.getString(2)));
            }
            System.out.println("Prof. Brown courses:");
            for (Course course : profBrownCourses){
                System.out.println(course.courseName+" by "+course.courseInstructor);
            }

            System.out.println();

//            ***** Q6 *****
            result = statement.executeQuery("select s.student_id, s.first_name, s.last_name from students as s inner join studentcourse as sc on s.student_id = sc.student_id where sc.course_id in (1, 2) group by s.student_id having count(distinct sc.course_id) = 2;\n");
            List<StudentInfo> enrolledMathematicsHistory = new ArrayList<>();
            while(result.next()){
                enrolledMathematicsHistory.add(new StudentInfo(result.getInt(1), result.getString(2), result.getString(3)));
            }
            System.out.println("Students enrolled both in Mathematics and History courses:");
            for (StudentInfo student : enrolledMathematicsHistory){
                FileTools.fileWriter("Q6.txt", student.studentId+" | "+student.firstName+" "+student.lastName);
                System.out.println(student.studentId+" | "+student.firstName+" "+student.lastName);
            }

            System.out.println();

//            ***** Q7 *****
            result = statement.executeQuery("select s.student_id, s.first_name, s.last_name from students as s left join studentcourse as sc on s.student_id = sc.student_id where sc.course_id is null;");
            List<StudentInfo> notEnrolledStudents = new ArrayList<>();
            while(result.next()){
                notEnrolledStudents.add(new StudentInfo(result.getInt(1), result.getString(2), result.getString(3)));
            }
            System.out.println("Students who are not enrolled in any course:");
            for (StudentInfo student : notEnrolledStudents){
                System.out.println(student.studentId+" | "+student.firstName+" "+student.lastName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}