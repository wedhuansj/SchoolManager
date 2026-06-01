package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCConnectionConfig {
    static final String URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "C7z#mK9!rQ2*pL4v";
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            return conn;
        }
        catch (SQLException e) {
            System.out.println("Lỗi kết nối");
        }
        return null;
    }
}