package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;

import java.util.ArrayList;

public interface GetRecentTransactionsUseCase {
    ArrayList<TransactionPresentationModel> getRecentTransactions() throws UserIsNotLoggedInException;
}
