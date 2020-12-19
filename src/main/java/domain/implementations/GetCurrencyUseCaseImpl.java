package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import domain.interfaces.GetCurrencyUseCase;

public class GetCurrencyUseCaseImpl implements GetCurrencyUseCase {

    @Inject
    private CredentialsManager credentialsManager;

    @Override
    public String getCurrency() {
        try {
            return credentialsManager.getLastLoggedInUser().getCurrency();
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
            return "LM";
        }
    }
}
