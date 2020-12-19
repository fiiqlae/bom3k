package domain.implementations;

import com.google.inject.Inject;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.TransactionDataModel;
import domain.interfaces.GetRecentTransactionsUseCase;
import presentation.models.TransactionPresentationModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GetRecentTransactionsUseCaseImpl implements GetRecentTransactionsUseCase {

    @Inject
    CredentialsManager credentialsManager;

    @Inject
    Database database;

    @Override
    public ArrayList<TransactionPresentationModel> getRecentTransactions() throws UserIsNotLoggedInException {
        long userId = credentialsManager.getLastLoggedInUser().getId();
        ArrayList<TransactionDataModel> recentTransactions = database.selectUserTransactions(userId);
        return recentTransactions.stream().map(dataModel -> new TransactionPresentationModel(
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
