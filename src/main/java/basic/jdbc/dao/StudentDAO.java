package basic.jdbc.dao;
import basic.jdbc.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    private final Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    // Create
    public boolean addStudent(Student student) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,student.getId());
            stmt.setString(2,student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setDate(4, student.getBirthday());
            stmt.setInt(5, student.getYearJoin());
            stmt.setString(6, student.getStudentStatus());
            stmt.setString(7, student.getMajor());
            stmt.setDouble(8, student.getGpa());
            stmt.setString(9, student.getStudentStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Student getStudent(int id) {
        String sql = "select * from student where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student.Builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .surname(rs.getString("surname"))
                            .birthday(rs.getDate("birthday"))
                            .yearJoin(rs.getInt("yearJoin"))
                            .studentStatus(Student.STATUS.valueOf(rs.getString("studentStatus")))
                            .major(rs.getString("major"))
                            .gpa(rs.getDouble("gpa"))
                            .studentStatus(Student.STATUS.valueOf(rs.getString("studentStatus")))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name = ?, surname = ?, birthday = ?, year_join = ?, faculty = ?, major = ?, gpa = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSurname());
            stmt.setDate(3, student.getBirthday());
            stmt.setInt(4, student.getYearJoin());
            stmt.setString(5, student.getFaculty());
            stmt.setString(6, student.getMajor());
            stmt.setDouble(7, student.getGpa());
            stmt.setString(8, student.getStudentStatus());
            stmt.setInt(9, student.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
