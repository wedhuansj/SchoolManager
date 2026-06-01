import controller.SchoolController;
import config.JDBCConnectionConfig;
public class Main {
    public static void main(String[] args) {
        SchoolController s = new SchoolController();
        JDBCConnectionConfig.getConnection();
        s.start();
    }
}