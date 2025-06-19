package jabc.kasidit;

import jabc.kasidit.config.DatabaseConfig;
import jabc.kasidit.dao.StudentDAO;
import jabc.kasidit.model.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

            //Create
            Student student = new Student.Builder()
                    .Id(1)
                    .Name("Kasidit")
                    .Surname("Kornsang")
                    .Birthday(Date.valueOf(LocalDate.of(2004, 6, 25)))
                    .YearJoin(65)
                    .Faculty("Engineering")
                    .Major("Computer")
                    .Gpa(0.00)
                    .StudentStatus(Student.STATUS.active)
                    .build();
            boolean created = studentDAO.addStudent(student);
            System.out.println(created ? "✅ User inserted." : "❌ Insert failed.");

            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
