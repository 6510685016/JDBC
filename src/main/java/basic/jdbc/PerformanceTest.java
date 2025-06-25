package basic.jdbc;

import basic.jdbc.config.ConnectionPool;
import basic.jdbc.config.DatabaseConfig;
import basic.jdbc.dao.StudentDAO;
import basic.jdbc.model.Student;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class PerformanceTest {
    private static final int TOTAL = 1000;

    public static void main(String[] args) throws Exception {
        testWithDriverManager();
        testWithHikariCP();
    }

    static void testWithDriverManager() throws Exception {
        System.out.println("\n🔵 Testing with DriverManager (No Pool)");
        long start = System.currentTimeMillis();


        for (int i = 1; i <= TOTAL; i++) {
            try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD)) {
                StudentDAO studentDAO = new StudentDAO(conn);
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
                studentDAO.deleteStudent(1);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken (DriverManager): " + (end - start) + " ms");
    }

    static void testWithHikariCP() throws Exception {
        System.out.println("\n🟢 Testing with HikariCP (Connection Pool)");

        long start = System.currentTimeMillis();

        for (int i = 1; i <= TOTAL; i++) {
            try (Connection conn = ConnectionPool.getDataSource().getConnection()) {
                StudentDAO studentDAO = new StudentDAO(conn);
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
                studentDAO.deleteStudent(1);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken (HikariCP): " + (end - start) + " ms");
    }
}
