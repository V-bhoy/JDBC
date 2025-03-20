import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class App {
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME  = "root";
    private static final String PASSWORD = "QBhoyar12#";
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Established connection successfully!");
        }catch(ClassNotFoundException | SQLException e){
             e.printStackTrace();
         } finally {
             try {
                 if (Objects.nonNull(connection)) {
                     connection.close();
                 }
             } catch (SQLException e) {
                e.printStackTrace();
            }
         
        }
    }
}
