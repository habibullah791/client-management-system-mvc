package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
    * This class is used to connect to the database
    * It has methods to connect and close the connection to the database
*/ 
public class DBHandler {
    private Connection conn;
    private final String DB_URL = "jdbc:mysql://localhost:3306/client_schedule";
    private final String USER = "root";
    private final String PASS = "root";

    /**
        * This method is called when a new instance of this class is created
    */
    public DBHandler() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading driver: " + e.getMessage());
        }
    }

    /**
        * This method is used to get the connection to the database
        * @return Connection
    */
    public Connection getConnection() {
        return conn;
    }

    /**
        * This method is used to connect to the database
        * @return String
    */
    public String connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the database!");
            return "Connected";
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return null;
    }

    /**
        * This method is used to close the connection to the database
    */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

}