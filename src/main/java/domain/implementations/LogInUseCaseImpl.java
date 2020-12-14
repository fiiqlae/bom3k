package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.models.UserAccountDataModel;
import di.modules.DataModule;
import domain.interfaces.LogInUseCase;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;

public class LogInUseCaseImpl implements LogInUseCase {
    private final CredentialsManager credentialsManager;

    public LogInUseCaseImpl(){
        Injector injector = Guice.createInjector(new DataModule());
        credentialsManager = injector.getInstance(CredentialsManager.class);
    }
    @Override
    public UserAccountPresentationModel logIn(String username, String password) throws LoginException {
        UserAccountDataModel dataModel = credentialsManager.logIn(username, password);
        return new UserAccountPresentationModel(dataModel.getUsername(),
                dataModel.getCountry(),
                dataModel.getCity(),
                dataModel.getCurrency());
    }
}
