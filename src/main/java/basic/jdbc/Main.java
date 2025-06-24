package basic.jdbc;

import basic.jdbc.config.DatabaseConfig;
import basic.jdbc.dao.CourseDAO;
import basic.jdbc.dao.EnrollmentDAO;
import basic.jdbc.dao.SemesterDAO;
import basic.jdbc.dao.StudentDAO;
import basic.jdbc.model.Course;
import basic.jdbc.model.Enrollment;
import basic.jdbc.model.Semester;
import basic.jdbc.model.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
            );
            System.out.println("✅ Connected to MySQL!");

            StudentDAO studentDAO = new StudentDAO(conn);
            CourseDAO courseDAO = new CourseDAO(conn);
            SemesterDAO semesterDAO = new SemesterDAO(conn);
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

            //Create
            Student student = new Student.Builder()
                    .id(1)
                    .name("Kasidit")
                    .surname("Kornsang")
                    .birthday(Date.valueOf(LocalDate.of(2004, 6, 25)))
                    .yearJoin(65)
                    .faculty("Engineering")
                    .major("Computer")
                    .gpa(0.00)
                    .studentStatus(Student.STATUS.active)
                    .build();
            boolean created = studentDAO.addStudent(student);

            // 2. สร้างวิชา
            Course course = new Course.Builder()
                    .courseId(1)
                    .courseCode("CS101")
                    .courseName("Introduction to CS")
                    .credit(3)
                    .build();
            courseDAO.addCourse(course);

            // 3. สร้างเทอม
            Semester semester = new Semester.Builder()
                    .semesterId(1)
                    .year(2025)
                    .term("1")
                    .build();
            semesterDAO.addSemester(semester);

            // 4. ลงทะเบียนเรียน
            Enrollment enrollment = new Enrollment.Builder()
                    .enrollmentId(1)
                    .studentId(1)
                    .courseId(1)
                    .semesterId(1)
                    .score(0)
                    .grade(null)
                    .build();
            boolean enrolled = enrollmentDAO.addEnrollment(enrollment);

            System.out.println(enrolled ? "✅ Enrollment successful." : "❌ Enrollment failed.");

            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
