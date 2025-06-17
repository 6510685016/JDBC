package jabc.kasidit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
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

            UserDAO userDAO = new UserDAO(conn);

            // ==== CREATE ====
            User newUser = new User(1, "Kasidit", "Korn", Date.valueOf("2000-01-01"), "Bangkok");
            boolean inserted = userDAO.insertUser(newUser);
            System.out.println(inserted ? "✅ User inserted." : "❌ Insert failed.");

            // ==== READ ====
            User fetchedUser = userDAO.getUserById(1);
            if (fetchedUser != null) {
                System.out.println("✅ User found: " + fetchedUser.getName());
            } else {
                System.out.println("❌ User not found.");
            }

            // ==== UPDATE ====
            fetchedUser.setAddress("Pathum Thani");
            boolean updated = userDAO.updateUser(fetchedUser);
            System.out.println(updated ? "✅ User updated." : "❌ Update failed.");

            // ==== READ ALL ====
            List<User> allUsers = userDAO.getAllUsers();
            System.out.println("📄 All users in DB:");
            for (User user : allUsers) {
                System.out.println(" - " + user.getName() + " (" + user.getAddress() + ")");
            }

            // ==== DELETE ====
            boolean deleted = userDAO.deleteUser(1);
            System.out.println(deleted ? "✅ User deleted." : "❌ Delete failed.");

            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
