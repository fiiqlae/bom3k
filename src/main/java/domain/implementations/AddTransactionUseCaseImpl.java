package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.AddTransactionUseCase;
import presentation.models.TransactionPresentationModel;
import presentation.models.TransactionSubmissionPresentationModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;

public class AddTransactionUseCaseImpl implements AddTransactionUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public void addTransaction(TransactionSubmissionPresentationModel transaction) throws UserIsNotLoggedInException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        Random random = new Random();
        long id = instant.toEpochMilli() + random.nextInt(120);
        TransactionDataModel dataModel = new TransactionDataModel(
                credentialsManager.getLastLoggedInUser().getId(),
                id,
                transaction.getName(),
                transaction.getKind(),
                transaction.isPeriodical(),
                String.valueOf(instant.toEpochMilli()),
                String.valueOf(transaction.getDueDate().atStartOfDay().atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli()),
                "generic category",
                transaction.getComment(),
                transaction.getSender(),
                transaction.getReceiver(),
                transaction.getAmount());
        database.createTransaction(dataModel);
    }
}
