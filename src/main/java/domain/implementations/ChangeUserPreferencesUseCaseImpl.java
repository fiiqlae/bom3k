package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;
import domain.interfaces.ChangeUserPreferencesUseCase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeUserPreferencesUseCaseImpl implements ChangeUserPreferencesUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public void setUserCountry(String country) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCountry(country);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserCity(String city) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCity(city);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserCurrency(String currency) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCurrency(currency);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
