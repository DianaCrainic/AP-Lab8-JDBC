package bonus;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * bonus part
 * DataManager class:
 * - manages a connection pool using HikariCP
 */
public class DataManager {
    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource hikariDataSource;
    private static DataManager instance;
    private static Connection connection;

    static {
        String url = "jdbc:postgresql://localhost:5432/musicalbums";
        String username = "dba";
        String password = "sql";

        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(100);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private DataManager() {

    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

}
