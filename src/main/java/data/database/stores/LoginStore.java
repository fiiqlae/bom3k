package data.database.stores;

import data.models.UserAccountDataModel;

import java.sql.SQLException;

public interface LoginStore {
    void logIn(long userId) throws SQLException;
    UserAccountDataModel getLastLoggedInUser();
    void logOut() throws SQLException;
}
