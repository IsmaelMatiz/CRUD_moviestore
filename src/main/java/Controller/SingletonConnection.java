package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;
    private static final String url = "jdbc:mysql://final-stage-sena.mysql.database.azure.com/moviestore";
    private static final String user = "admins";
    private static final String password = "r#M4o8%A!u724Cuny5";

    private SingletonConnection(){}

    public static Connection GetDBConnection() throws SQLException {
        connection = DriverManager.getConnection(url,user,password);

        return  connection;
    }
}
