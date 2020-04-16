package bonus;

import db.Database;

import java.sql.Connection;

/**
 * bonus part
 * Connection type: pool-connection
 */
public class PoolConn implements ConnectionManager {

    @Override
    public Connection getConnection() {
        return Database.getInstance().getConnection();
    }

    @Override
    public String getType() {
        return "connection-pool";
    }
}
