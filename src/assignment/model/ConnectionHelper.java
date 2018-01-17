package assignment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHelper {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptech_fpt?useUnicode=true&characterEncoding=UTF-8", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
