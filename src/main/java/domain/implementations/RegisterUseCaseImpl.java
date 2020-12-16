package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.exceptions.UserExistsException;
import data.models.UserAccountDataModel;
import di.modules.DataModule;
import domain.interfaces.RegisterUseCase;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;

public class RegisterUseCaseImpl implements RegisterUseCase {

    private final CredentialsManager credentialsManager;

    public RegisterUseCaseImpl(){
        Injector injector = Guice.createInjector(new DataModule());
        credentialsManager = injector.getInstance(CredentialsManager.class);
    }

    @Override
    public boolean register(String username, String password) throws UserExistsException {
        return credentialsManager.registerUser(username, password);
    }

    @Override
    public UserAccountPresentationModel registerAndLogIn(String username, String password) throws UserExistsException, LoginException {
        if(register(username, password)) {
            UserAccountDataModel dataModel = credentialsManager.logIn(username, password);
            return new UserAccountPresentationModel(dataModel.getUsername(),
                    dataModel.getCountry(),
                    dataModel.getCity(),
                    dataModel.getCurrency());
        } else {
            return null;
        }

    }
}
