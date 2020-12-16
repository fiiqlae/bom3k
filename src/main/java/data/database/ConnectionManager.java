package data.database;

import java.sql.Connection;

public interface ConnectionManager {
    void establishConnection();
    void terminateConnection(Connection connection);
    Connection getActiveConnection();
}
