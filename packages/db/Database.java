package db;

import java.sql.*;
import java.util.*;

/**
 * singleton class Database that manages a connection to the database
 */
public class Database implements AutoCloseable {

    private static Database instance;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private Database() {
        String url = "jdbc:postgresql://localhost:5432/musicalbums";
        try {
            connection = DriverManager.getConnection(url, "dba", "sql");
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
