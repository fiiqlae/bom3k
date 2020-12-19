package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.exceptions.UserExistsException;
import data.models.UserAccountDataModel;
import domain.interfaces.RegisterUseCase;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class RegisterUseCaseImpl implements RegisterUseCase {

    @Inject
    private CredentialsManager credentialsManager;

    @Override
    public boolean register(String username, String password) throws UserExistsException {
        try {
            return credentialsManager.registerUser(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public UserAccountPresentationModel registerAndLogIn(String username, String password) throws UserExistsException, LoginException {
        if(register(username, password)) {
            UserAccountDataModel dataModel = null;
            try {
                dataModel = credentialsManager.logIn(username, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return new UserAccountPresentationModel(dataModel.getUsername(),
                    dataModel.getCountry(),
                    dataModel.getCity(),
                    dataModel.getCurrency());
        } else {
            return null;
        }

    }
}
