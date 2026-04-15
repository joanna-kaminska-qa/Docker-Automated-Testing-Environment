package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {

    // Ta metoda przyjmie dynamiczny URL od Testcontainers
    public static int countRecords(String jdbcUrl, String user, String pass, String tableName) {
        int count = 0;
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Błąd bazy: " + e.getMessage());
        }
        return count;
    }
}