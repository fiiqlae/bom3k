package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.AddTransactionUseCase;
import presentation.models.TransactionPresentationModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class AddTransactionUseCaseImpl implements AddTransactionUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public void addTransaction(TransactionPresentationModel transaction) throws UserIsNotLoggedInException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        Random random = new Random();
        long id = instant.toEpochMilli() + random.nextInt(120);
        TransactionDataModel dataModel = new TransactionDataModel(
                credentialsManager.getLastLoggedInUser().getId(),
                id,
                transaction.getTransactionName(),
                transaction.getKind(),
                transaction.isPeriodical(),
                String.valueOf(instant.toEpochMilli()),
                transaction.getDueDate(),
                transaction.getCategory(),
                transaction.getCategory(),
                transaction.getSenderName(),
                transaction.getReceiverName(),
                transaction.getAmount());
        database.createTransaction(dataModel);
    }
}
