package domain.interfaces;

import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionSubmissionPresentationModel;

public interface AddPeriodicalTransactionUseCase {
    void addPeriodicalTransaction(TransactionSubmissionPresentationModel transaction) throws UserIsNotLoggedInException;
}
