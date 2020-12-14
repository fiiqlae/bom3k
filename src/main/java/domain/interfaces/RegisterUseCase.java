package domain.interfaces;

import data.database.CredentialsManagerImpl;
import data.exceptions.UserExistsException;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;

public interface RegisterUseCase {
    boolean register(String username, String password) throws UserExistsException;
    UserAccountPresentationModel registerAndLogIn(String username, String password) throws UserExistsException, LoginException;
}
