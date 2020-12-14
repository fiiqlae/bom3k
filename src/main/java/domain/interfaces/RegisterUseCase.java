package domain.interfaces;

import data.database.CredentialsManagerImpl;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;

public interface RegisterUseCase {
    boolean register(String username, String password) throws CredentialsManagerImpl.UserExistsException;
    UserAccountPresentationModel registerAndLogIn(String username, String password) throws CredentialsManagerImpl.UserExistsException, LoginException;
}
