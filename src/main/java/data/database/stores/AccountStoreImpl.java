package data.database.stores;

import commonDefenitions.DatabaseConfig;
import data.database.Database;
import data.database.Mappings;
import data.models.UserAccountDataModel;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountStoreImpl implements AccountStore {

    private final Connection activeConnection;
    private final Mappings mappings;

    public AccountStoreImpl(Connection activeConnection) {
        this.activeConnection = activeConnection;
        this.mappings = new Mappings(activeConnection);
    }

    @Override
    public void createUser(UserAccountDataModel userAccount) {
        try {
            PreparedStatement statement = mappings.toInsertUserQuery(userAccount);
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void alterUser(UserAccountDataModel targetUserAccount) {
        try {
            PreparedStatement statement = mappings.toUpdateUserQuery(targetUserAccount);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteUser(UserAccountDataModel userAccount) {
        try {
            PreparedStatement statement = activeConnection.prepareStatement(DatabaseConfig.queryDeleteUser);
            statement.setLong(1, userAccount.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public UserAccountDataModel selectUserByPasswordHash(String hash) {
        UserAccountDataModel queryResult;
        try {
            PreparedStatement statement =
                    activeConnection.prepareStatement(DatabaseConfig.querySelectUserByHash);
            statement.setString(1, hash);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return mappings.toUserAccountDataModel(resultSet);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
}
