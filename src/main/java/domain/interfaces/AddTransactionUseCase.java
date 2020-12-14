package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;

public interface AddTransactionUseCase {
    void addTransaction(TransactionPresentationModel transaction) throws UserIsNotLoggedInException;
}
