package data.database.stores;

import commonDefenitions.DatabaseConfig;
import data.database.Database;
import data.database.Mappings;
import data.models.UserAccountDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginStoreImpl implements LoginStore{

    private final Connection activeConnection;
    private final AccountStore accountStore;

    public LoginStoreImpl(Connection activeConnection){
        this.activeConnection = activeConnection;
        this.accountStore = new AccountStoreImpl(activeConnection);
    }

    @Override
    public void logIn(long userId) throws SQLException {
        Random r = new Random();
        long transactionId = r.nextLong();
        PreparedStatement statement = activeConnection.prepareStatement(DatabaseConfig.queryLogIn);
        statement.setLong(1, transactionId);
        statement.setLong(2, userId);
        statement.execute();
    }

    @Override
    public UserAccountDataModel getLastLoggedInUser() {
        UserAccountDataModel queryResult;
        try {
            PreparedStatement statement =
                    activeConnection.prepareStatement(DatabaseConfig.querySelectLoggedInUser);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                System.out.println("lu: " + resultSet.getLong(2));
                return accountStore.selectUserById(resultSet.getLong(2));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public void logOut() throws SQLException {
        PreparedStatement statement = activeConnection.prepareStatement(DatabaseConfig.queryLogOut);
        statement.execute();
    }
}
