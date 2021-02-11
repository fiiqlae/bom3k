package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.AlterTransactionUseCase;
import presentation.models.TransactionPresentationModel;
import presentation.models.TransactionSubmissionPresentationModel;

import java.time.ZoneOffset;
import java.util.ArrayList;

public class AlterTransactionUseCaseImpl implements AlterTransactionUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    private TransactionDataModel getTargetTransaction(int listPosition) throws UserIsNotLoggedInException {
        long userId = credentialsManager.getLastLoggedInUser().getId();
        ArrayList<TransactionDataModel> transactions = database.selectUserTransactions(userId);
        return transactions.get(listPosition);
    }

    @Override
    public void alterTransaction(TransactionSubmissionPresentationModel target, int listPosition) throws UserIsNotLoggedInException {
        TransactionDataModel targetDataModel = getTargetTransaction(listPosition);
        targetDataModel.setTransactionName(target.getName());
        targetDataModel.setReceiverName(target.getReceiver());
        targetDataModel.setSenderName(target.getSender());
        targetDataModel.setDueDate(
                String.valueOf(target.getDueDate().atStartOfDay().atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli())
        );
        targetDataModel.setCategory("generic category");
        targetDataModel.setComment(target.getComment());
        targetDataModel.setPeriodical(target.isPeriodical());
        targetDataModel.setKind(target.getKind());
        targetDataModel.setAmount(target.getAmount());
        database.alterTransaction(targetDataModel);
    }
}
