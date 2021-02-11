package domain.implementations;

import com.google.inject.Inject;
import commonDefenitions.TransactionKind;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.CalculateAllowanceUseCase;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.stream.Stream;

public class CalculateAllowanceUseCaseImpl implements CalculateAllowanceUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public String getAllowance() throws UserIsNotLoggedInException {
        long userId = credentialsManager.getLastLoggedInUser().getId();
        ArrayList<TransactionDataModel> recentTransactions = database.selectUserTransactions(userId);
        Stream<TransactionDataModel> thisMonthTransactions = recentTransactions.stream().filter(t ->
                LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(Long.parseLong(t.getTimestamp())), TimeZone.getDefault().toZoneId()
                ).getMonth() == LocalDateTime.now().getMonth());
        return String.format("%.2f", thisMonthTransactions.map(t -> t.getKind() == TransactionKind.SPENDING? t.getAmount()*-1 : t.getAmount())
                .reduce(0.0f, Float::sum)*credentialsManager.getLastLoggedInUser().getAllowancePercentage()/100);
    }
}
