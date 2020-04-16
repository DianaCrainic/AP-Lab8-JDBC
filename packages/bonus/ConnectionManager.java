package bonus;

import java.sql.Connection;

/**
 * bonus part
 * ConnectionManager - an interface
 * for classes that use getConnection method : PoolConn and SingletonConn
 */
public interface ConnectionManager {
    Connection getConnection();
    String getType();
}
