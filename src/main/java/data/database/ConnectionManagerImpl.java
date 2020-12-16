package data.database;

import commonDefenitions.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManagerImpl implements ConnectionManager{

    private Connection activeConnection;

    @Override
    public void establishConnection() {
        try {
            activeConnection = DriverManager.getConnection(DatabaseConfig.dbUrl,
                    DatabaseConfig.dbUser,
                    DatabaseConfig.dbPassword);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public Connection getActiveConnection() {
        if(activeConnection == null) establishConnection();
        return activeConnection;
    }

    @Override
    public void terminateConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
