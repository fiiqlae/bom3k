package domain.interfaces;

import commonDefenitions.TransactionKind;
import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;
import presentation.models.TransactionSubmissionPresentationModel;

public interface AlterTransactionUseCase {
    void alterTransaction(TransactionSubmissionPresentationModel target, int listPosition) throws UserIsNotLoggedInException;
}
