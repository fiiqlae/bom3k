package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;
import domain.interfaces.UserPreferencesUseCase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPreferencesUseCaseImpl implements UserPreferencesUseCase {

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
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
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
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
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
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserAllowance(float allowance) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setAllowancePercentage(allowance);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public String getUserCountry() {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            return user.getCountry();
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }

    @Override
    public String getUserCity() {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            return user.getCity();
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }

    @Override
    public String getUserCurrency() {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            return user.getCurrency();
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }

    @Override
    public float getUserAllowance() {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            return user.getAllowancePercentage();
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(UserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return 25.0f;
    }
}
