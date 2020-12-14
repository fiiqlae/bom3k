package data.database;

import data.exceptions.UserExistsException;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;

import javax.security.auth.login.LoginException;

public interface CredentialsManager {
    UserAccountDataModel logIn(String userName, String password) throws LoginException;
    boolean registerUser(String userName, String password) throws UserExistsException;
    UserAccountDataModel getLastLoggedInUser() throws UserIsNotLoggedInException;
}
