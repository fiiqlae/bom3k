package domain.implementations;

import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.AddPeriodicalTransactionUseCase;
import presentation.models.TransactionSubmissionPresentationModel;

import javax.inject.Inject;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;

public class AddPeriodicalTransactionUseCaseImpl implements AddPeriodicalTransactionUseCase {

    @Inject
    private CredentialsManager credentialsManager;

    @Inject
    private Database database;

    @Override
    public void addPeriodicalTransaction(TransactionSubmissionPresentationModel transaction) throws UserIsNotLoggedInException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            long id = instant.toEpochMilli() + random.nextInt(120) + i;
            TransactionDataModel dataModel = new TransactionDataModel(
                    credentialsManager.getLastLoggedInUser().getId(),
                    id,
                    transaction.getName(),
                    transaction.getKind(),
                    transaction.isPeriodical(),
                    String.valueOf(instant.toEpochMilli()),
                    getScheduledDueDateWithOffset(transaction.getDueDate(), i),
                    "generic category",
                    transaction.getComment(),
                    transaction.getSender(),
                    transaction.getReceiver(),
                    transaction.getAmount());
            database.createTransaction(dataModel);
        }
    }
    private String getScheduledDueDateWithOffset(LocalDate dueDate, int monthsOffset){
        return String.valueOf(dueDate.plus(monthsOffset, ChronoUnit.MONTHS).atStartOfDay()
                .atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli());
    }
}
