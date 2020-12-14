package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import di.modules.DataModule;
import domain.interfaces.AddTransactionUseCase;
import presentation.models.TransactionPresentationModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;
import java.util.Random;

public class AddTransactionUseCaseImpl implements AddTransactionUseCase {

    CredentialsManager credentialsManager;
    Database database;

    public AddTransactionUseCaseImpl() {
        Injector injector = Guice.createInjector(new DataModule());
        credentialsManager = injector.getInstance(CredentialsManager.class);
        database = injector.getInstance(Database.class);
    }

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
                instant.toString(),
                transaction.getDueDate(),
                transaction.getCategory(),
                transaction.getCategory(),
                transaction.getSenderName(),
                transaction.getReceiverName()
        );
        database.createTransaction(dataModel);
    }
}
