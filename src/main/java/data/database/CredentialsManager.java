package data.database;

import data.exceptions.UserExistsException;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public interface CredentialsManager {
    UserAccountDataModel logIn(String userName, String password) throws LoginException, SQLException;
    boolean registerUser(String userName, String password) throws UserExistsException, SQLException;
    UserAccountDataModel getLastLoggedInUser() throws UserIsNotLoggedInException;
    void logOut() throws SQLException;
}
