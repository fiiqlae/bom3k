package presentation.viewmodels;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ibm.jvm.Log;
import data.exceptions.UserExistsException;
import di.modules.ApplicationModule;
import domain.interfaces.LogInUseCase;
import domain.interfaces.RegisterUseCase;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

public class LoginViewModel {

    RegisterUseCase registerUseCase;
    LogInUseCase logInUseCase;

    public LoginViewModel() {
        Injector i = Guice.createInjector(new ApplicationModule());
        registerUseCase = i.getInstance(RegisterUseCase.class);
        logInUseCase = i.getInstance(LogInUseCase.class);
    }

    public void login(String login, String pwd) {
        try {
            logInUseCase.logIn(login, pwd);
        } catch (LoginException e) {
            try {
                registerUseCase.registerAndLogIn(login, pwd);
            } catch (UserExistsException | LoginException userExistsException) {
                userExistsException.printStackTrace();
            }
        }
    }
}
