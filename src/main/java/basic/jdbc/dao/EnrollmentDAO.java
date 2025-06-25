package basic.jdbc.dao;

import basic.jdbc.model.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private final Connection conn;

    public EnrollmentDAO(Connection conn) {
        this.conn = conn;
    }

    // Create
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (enrollment_id, student_id, course_id, semester_id, score, grade) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollment.getEnrollmentId());
            stmt.setInt(2, enrollment.getStudentId());
            stmt.setInt(3, enrollment.getCourseId());
            stmt.setInt(4, enrollment.getSemesterId());
            stmt.setDouble(5, enrollment.getScore());
            stmt.setString(6, enrollment.getGrade());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Enrollment getEnrollment(int id) {
        String sql = "SELECT * FROM enrollment WHERE enrollment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Enrollment.Builder()
                            .enrollmentId(rs.getInt("enrollment_id"))
                            .studentId(rs.getInt("student_id"))
                            .courseId(rs.getInt("course_id"))
                            .semesterId(rs.getInt("semester_id"))
                            .score(rs.getDouble("score"))
                            .grade(rs.getString("grade"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE enrollment SET student_id = ?, course_id = ?, semester_id = ?, score = ?, grade = ? WHERE enrollment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setInt(3, enrollment.getSemesterId());
            stmt.setDouble(4, enrollment.getScore());
            stmt.setString(5, enrollment.getGrade());
            stmt.setInt(6, enrollment.getEnrollmentId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteEnrollment(int id) {
        String sql = "DELETE FROM enrollment WHERE enrollment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read All
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollment";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                enrollments.add(new Enrollment.Builder()
                        .enrollmentId(rs.getInt("enrollment_id"))
                        .studentId(rs.getInt("student_id"))
                        .courseId(rs.getInt("course_id"))
                        .semesterId(rs.getInt("semester_id"))
                        .score(rs.getDouble("score"))
                        .grade(rs.getString("grade"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
