package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;

import java.util.ArrayList;

public interface GetScheduledTransactionsUseCase {
    ArrayList<TransactionPresentationModel> getScheduledTransactions() throws UserIsNotLoggedInException;
}
