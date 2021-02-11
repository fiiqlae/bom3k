package domain.implementations;

import com.google.inject.Inject;
import commonDefenitions.TransactionKind;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.GetScheduledTransactionsUseCase;
import presentation.models.TransactionPresentationModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetScheduledTransactionsUseCaseImpl implements GetScheduledTransactionsUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public ArrayList<TransactionPresentationModel> getScheduledTransactions() throws UserIsNotLoggedInException {
        long userId = credentialsManager.getLastLoggedInUser().getId();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        ArrayList<TransactionDataModel> recentTransactions = database.selectUserTransactions(userId);
        Stream<TransactionDataModel> thisMonthRecurringPayments = recentTransactions.stream().filter(t -> {
            boolean belongsToThisMonth =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(Long.parseLong(t.getDueDate())), TimeZone.getDefault().toZoneId()
                    ).getMonth() == LocalDateTime.now().getMonth();
            boolean willHappenInTheFuture = Long.parseLong(t.getDueDate()) > instant.toEpochMilli();
            boolean isPeriodicalSpending = t.getKind() == TransactionKind.SPENDING && t.isPeriodical();
            return (belongsToThisMonth && willHappenInTheFuture && isPeriodicalSpending);
        });
        return thisMonthRecurringPayments.map(dataModel -> new TransactionPresentationModel(
                dataModel.getTransactionName(),
                dataModel.getKind(),
                dataModel.isPeriodical(),
                dataModel.getTimestamp(),
                dataModel.getDueDate(),
                dataModel.getCategory(),
                dataModel.getComment(),
                dataModel.getSenderName(),
                dataModel.getReceiverName(),
                dataModel.getAmount()
        )).distinct().collect(Collectors.toCollection(ArrayList::new));

    }
}
