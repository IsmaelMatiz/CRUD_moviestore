package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;
    private static final String url = "jdbc:mysql://gmovile-db-movie-store.mysql.database.azure.com/moviestore";
    private static final String user = "peers";
    private static final String password = "AppMoviles2023";

    private SingletonConnection(){}

    public static Connection GetDBConnection() throws SQLException {
        connection = DriverManager.getConnection(url,user,password);

        return  connection;
    }
}
