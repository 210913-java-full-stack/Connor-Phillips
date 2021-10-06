package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionManager {
    private static Connection conn;

    private ConnectionManager() {

    }

    public static Connection getConnection() {
        if(conn == null) {
            try {
                Properties props = new Properties();
                FileReader connProps = new FileReader("src/main/resources/connection.properties");
                props.load(connProps);

                String connString = "jdbc:mariadb://"
                        + props.getProperty("hostname")
                        + ":" + props.getProperty("port")
                        + "/" + props.getProperty("databaseName")
                        + "?user=" + props.getProperty("user")
                        + "&password=" + props.getProperty("password");

                conn = DriverManager.getConnection(connString);
            } catch(SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
