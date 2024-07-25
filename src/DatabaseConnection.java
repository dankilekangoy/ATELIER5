import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://sql.freedb.tech/freedb_Mybasedata";
    private static final String USER = "freedb_dankileka";
    private static final String PASSWORD = "6b%FEACtB@PGxX4";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
