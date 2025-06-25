package basic.jdbc;

import basic.jdbc.config.ConnectionPool;

import java.sql.*;

import java.sql.Connection;
import java.time.LocalDate;


public class Batch {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionPool.getDataSource().getConnection()) {
            conn.setAutoCommit(false);

            // === Create: Normal ===
            long startNormal = System.nanoTime();
            for (int i = 1; i <= 10; i++) {
                String sql = "INSERT INTO student (ID, name, surname, birthday, year_join, faculty, major, gpa, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, 100 + i);
                    stmt.setString(2, "Name" + i);
                    stmt.setString(3, "Surname" + i);
                    stmt.setDate(4, Date.valueOf(LocalDate.of(2000, 1, i)));
                    stmt.setInt(5, 65);
                    stmt.setString(6, "Engineering");
                    stmt.setString(7, "Computer");
                    stmt.setDouble(8, 4.0);
                    stmt.setString(9, "active");
                    stmt.executeUpdate();
                }
            }

            conn.commit();
            long endNormal = System.nanoTime();
            System.out.println("⏱️ Normal INSERT time: " + (endNormal - startNormal) / 1_000_000 + " ms");

            // === Delete: Normal ===
            for (int i = 1; i <= 10; i++) {
                String sql = "DELETE FROM student WHERE ID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, 100 + i);
                    stmt.executeUpdate();
                }
            }

            // === Create: Batch ===
            long startBatch = System.nanoTime();
            String sql = "INSERT INTO student (ID, name, surname, birthday, year_join, faculty, major, gpa, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 1; i <= 10; i++) {
                    stmt.setInt(1, 200 + i);
                    stmt.setString(2, "Name" + i);
                    stmt.setString(3, "Surname" + i);
                    stmt.setDate(4, Date.valueOf(LocalDate.of(2000, 1, i)));
                    stmt.setInt(5, 65);
                    stmt.setString(6, "Engineering");
                    stmt.setString(7, "Computer");
                    stmt.setDouble(8, 4.0);
                    stmt.setString(9, "active");

                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
            long endBatch = System.nanoTime();
            System.out.println("⏱️ Batch INSERT time: " + (endBatch - startBatch) / 1_000_000 + " ms");

            // === Delete: Batch ===
            String deleteSql = "DELETE FROM student WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
                for (int i = 1; i <= 10; i++) {
                    stmt.setInt(1, 200 + i);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            System.out.println("✅ Test complete.");
        }
    }

}
