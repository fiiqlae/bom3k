package domain.interfaces;

import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;

public interface LogInUseCase {
    UserAccountPresentationModel logIn(String username, String password) throws LoginException;
}
