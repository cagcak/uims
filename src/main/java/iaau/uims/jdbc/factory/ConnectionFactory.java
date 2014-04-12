/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 *********************************************
 * A class that other classes may use
 * to get a connection to the data storage.  This static
 * class manages the connection and associated resources.
 * We can use this calls to test connecting to the data store.
 */

package iaau.uims.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Çağrı Çakır
 */
public class ConnectionFactory {
    
    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();
    
    public static final String URL = "jdbc:mysql://localhost:1990/dbuims?zeroDateTimeBehavior=convertToNull";
    public static final String USER = "root";
    public static final String PASSWORD = "00011011";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    //private constructor
    private ConnectionFactory() {
        System.out.println("\n*****************\nConnecting to database...\n*******************\n");
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: exception loading driver class");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
    
    public static Connection getConnection() {
        return instance.createConnection();
    }
    
}
