package db;

import java.sql.*;
import java.util.*;

public class Database {

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
}
