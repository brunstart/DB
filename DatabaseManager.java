import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private static String className = "org.mariadb.jdbc.Driver";
    private static String url = "jdbc:mariadb://127.0.01:3306/jdbc";
    private static String ID = "root";
    private static String PW = "tmdals123";

    static {
        try {
            Class.forName(className);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  Connection getConnection() {
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(url, ID, PW);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
