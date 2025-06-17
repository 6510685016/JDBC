package jabc.kasidit;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class is used to create a JDBC 
 * connection with MySql DB.
 * @author w3schools
 */
public class JDBCMySqlTest {
    //JDBC and database properties.
    private static final String DB_DRIVER =
            "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/test1";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String args[]){
        Connection conn = null;
        try{
            //Register the JDBC driver
            Class.forName(DB_DRIVER);

            //Open the connection
            conn = DriverManager.
                    getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            if(conn != null){
                System.out.println("Successfully connected.");
            }else{
                System.out.println("Failed to connect.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}