package data.database;

import data.models.UserAccountDataModel;

import javax.security.auth.login.LoginException;

public interface CredentialsManager {
    UserAccountDataModel logIn(String userName, String password) throws LoginException;
    boolean registerUser(String userName, String password) throws CredentialsManagerImpl.UserExistsException;
}
