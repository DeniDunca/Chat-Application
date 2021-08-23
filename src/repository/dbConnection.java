package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private Connection connection = null;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String LINK = "jdbc:postgresql://localhost:5432/Chat Application";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgre";

    public dbConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(LINK,USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database successfully");
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
