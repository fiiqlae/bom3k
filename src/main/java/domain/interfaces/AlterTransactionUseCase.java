package domain.interfaces;

import commonDefenitions.TransactionKind;
import data.exceptions.UserIsNotLoggedInException;
import presentation.models.TransactionPresentationModel;

public interface AlterTransactionUseCase {
    void alterTransaction(TransactionPresentationModel target, int listPosition) throws UserIsNotLoggedInException;
}
