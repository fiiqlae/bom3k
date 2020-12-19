package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.models.UserAccountDataModel;
import domain.interfaces.LogInUseCase;
import presentation.models.UserAccountPresentationModel;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class LogInUseCaseImpl implements LogInUseCase {

    @Inject
    private CredentialsManager credentialsManager;

    @Override
    public UserAccountPresentationModel logIn(String username, String password) throws LoginException {
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
    }
}
