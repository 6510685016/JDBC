package basic.jdbc;

import basic.jdbc.config.ConnectionPool;
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
import java.sql.SQLException;
import java.time.LocalDate;

public class Tranmission {
    public static void main(String[] args) {
        try (Connection conn = ConnectionPool.getDataSource().getConnection()) {
            conn.setAutoCommit(false); // ปิด Auto-commit

            System.out.println("✅ Connected to MySQL via Connection Pool!");

            StudentDAO studentDAO = new StudentDAO(conn);
            CourseDAO courseDAO = new CourseDAO(conn);
            SemesterDAO semesterDAO = new SemesterDAO(conn);
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO(conn);

            // 1. สร้างนักเรียน
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
            boolean studentCreated = studentDAO.addStudent(student);

            // 2. สร้างวิชา
            Course course = new Course.Builder()
                    .courseId(1)
                    .courseCode("CS101")
                    .courseName("Introduction to CS")
                    .credit(3)
                    .build();
            boolean courseCreated = courseDAO.addCourse(course);

            // 3. สร้างเทอม
            Semester semester = new Semester.Builder()
                    .semesterId(1)
                    .year(2025)
                    .term("1")
                    .build();
            boolean semesterCreated = semesterDAO.addSemester(semester);

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

            if (studentCreated && courseCreated && semesterCreated && enrolled) {
                conn.commit(); // ✅ commit ถ้าทุกอย่างผ่าน
                System.out.println("✅ Enrollment successful.");
            } else {
                conn.rollback(); // ❌ rollback ถ้ามีอันใดอันหนึ่งล้มเหลว
                System.out.println("❌ Some insertions failed. Rolled back.");
            }

            // Clean up (จะไม่ commit/rollback ส่วนนี้ ให้ทำแยก หรือใช้ transaction ใหม่)
            enrollmentDAO.deleteEnrollment(1);
            semesterDAO.deleteSemester(1);
            courseDAO.deleteCourse(1);
            studentDAO.deleteStudent(1);

            System.out.println("🧹 Cleanup completed.");

        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
