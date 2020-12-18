package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Injector;
import commonDefenitions.TransactionKind;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import di.modules.DataModule;
import domain.interfaces.AlterTransactionUseCase;
import presentation.models.TransactionPresentationModel;

import java.util.ArrayList;

public class AlterTransactionUseCaseImpl implements AlterTransactionUseCase {

    CredentialsManager credentialsManager;
    Database database;

    public AlterTransactionUseCaseImpl() {
        Injector injector = Guice.createInjector(new DataModule());
        credentialsManager = injector.getInstance(CredentialsManager.class);
        database = injector.getInstance(Database.class);
    }

    private TransactionDataModel getTargetTransaction(int listPosition) throws UserIsNotLoggedInException {
        long userId = credentialsManager.getLastLoggedInUser().getId();
        ArrayList<TransactionDataModel> transactions = database.selectUserTransactions(userId);
        return transactions.get(listPosition);
    }

    @Override
    public void alterTransaction(TransactionPresentationModel target, int listPosition) throws UserIsNotLoggedInException {
        TransactionDataModel targetDataModel = getTargetTransaction(listPosition);
        targetDataModel.setTransactionName(target.getTransactionName());
        targetDataModel.setReceiverName(target.getReceiverName());
        targetDataModel.setSenderName(target.getSenderName());
        targetDataModel.setDueDate(target.getDueDate());
        targetDataModel.setCategory(target.getCategory());
        targetDataModel.setComment(target.getComment());
        targetDataModel.setPeriodical(target.isPeriodical());
        targetDataModel.setKind(target.getKind());
        targetDataModel.setAmount(target.getAmount());
        database.alterTransaction(targetDataModel);
    }
}
