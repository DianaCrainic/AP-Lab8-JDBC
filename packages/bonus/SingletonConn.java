package bonus;

import db.Database;

import java.sql.Connection;

/**
 * bonus part
 * Connection type: singleton connection
 */
public class SingletonConn implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return Database.getInstance().getConnection();
    }

    @Override
    public String getType() {
        return "singleton";
    }
}
