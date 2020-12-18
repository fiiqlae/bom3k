package presentation.viewmodels;

import javafx.collections.ObservableList;
import presentation.models.TransactionPresentationModel;

public interface HomeViewModel {
    ObservableList<TransactionPresentationModel> getRecentTransactions();
    ObservableList<TransactionPresentationModel> getScheduledTransactions();
    ObservableList<Float> getAllowanceRemaining();
}
