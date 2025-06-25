package basic.jdbc.dao;

import basic.jdbc.model.Semester;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAO {
    private final Connection conn;

    public SemesterDAO(Connection conn) {
        this.conn = conn;
    }

    // Create
    public boolean addSemester(Semester semester) {
        String sql = "INSERT INTO semester (semester_id ,year, term) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, semester.getSemesterId());
            stmt.setInt(2, semester.getYear());
            stmt.setString(3, semester.getTerm());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Semester getSemester(int id) {
        String sql = "SELECT * FROM semester WHERE semester_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Semester.Builder()
                            .semesterId(rs.getInt("semester_id"))
                            .year(rs.getInt("year"))
                            .term(rs.getString("term"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean updateSemester(Semester semester) {
        String sql = "UPDATE semester SET year = ?, term = ? WHERE semester_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, semester.getYear());
            stmt.setString(2, semester.getTerm());
            stmt.setInt(3, semester.getSemesterId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteSemester(int id) {
        String sql = "DELETE FROM semester WHERE semester_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read All
    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        String sql = "SELECT * FROM semester";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                semesters.add( new Semester.Builder()
                        .semesterId(rs.getInt("semester_id"))
                        .year(rs.getInt("year"))
                        .term(rs.getString("term"))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semesters;
    }
}
