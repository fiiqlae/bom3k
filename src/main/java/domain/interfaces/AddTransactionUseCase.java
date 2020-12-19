package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;
import presentation.models.TransactionSubmissionPresentationModel;

public interface AddTransactionUseCase {
    void addTransaction(TransactionSubmissionPresentationModel transaction) throws UserIsNotLoggedInException;
}
