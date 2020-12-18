package presentation.viewmodels;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.exceptions.UserIsNotLoggedInException;
import di.modules.ApplicationModule;
import domain.interfaces.CalculateAllowanceUseCase;
import domain.interfaces.GetRecentTransactionsUseCase;
import domain.interfaces.GetScheduledTransactionsUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.models.TransactionPresentationModel;

import java.util.ArrayList;

public class HomeViewModelImpl implements HomeViewModel{
    private ObservableList<TransactionPresentationModel> recentTransactions;
    private ObservableList<TransactionPresentationModel> scheduledTransactions;
    private ObservableList<Float> allowanceRemaining;


    private GetRecentTransactionsUseCase getRecentTransactionsUseCase;
    private CalculateAllowanceUseCase calculateAllowanceUseCase;
    private GetScheduledTransactionsUseCase getScheduledTransactionsUseCase;

    public HomeViewModelImpl() {
        Injector i = Guice.createInjector(new ApplicationModule());
        getRecentTransactionsUseCase = i.getInstance(GetRecentTransactionsUseCase.class);
        calculateAllowanceUseCase = i.getInstance(CalculateAllowanceUseCase.class);
        getScheduledTransactionsUseCase = i.getInstance(GetScheduledTransactionsUseCase.class);
        try {
            recentTransactions = FXCollections.observableArrayList(getRecentTransactionsUseCase.getRecentTransactions());
            scheduledTransactions = FXCollections.observableArrayList(getScheduledTransactionsUseCase.getScheduledTransactions());
            allowanceRemaining = FXCollections.observableArrayList(calculateAllowanceUseCase.getAllowance());
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TransactionPresentationModel> getRecentTransactions() {
        try {
            ArrayList<TransactionPresentationModel> newTransactions =
                    getRecentTransactionsUseCase.getRecentTransactions();
            recentTransactions.removeAll();
            recentTransactions.addAll(newTransactions);
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
        return recentTransactions;
    }

    public ObservableList<TransactionPresentationModel> getScheduledTransactions() {
        try {
            ArrayList<TransactionPresentationModel> newTransactions =
                getScheduledTransactionsUseCase.getScheduledTransactions();
            scheduledTransactions.removeAll();
            scheduledTransactions.addAll(newTransactions);
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
        return scheduledTransactions;
    }

    public ObservableList<Float> getAllowanceRemaining() {
        try {
            float newAllowance = calculateAllowanceUseCase.getAllowance();
            allowanceRemaining.removeAll();
            allowanceRemaining.add(newAllowance);
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
        return allowanceRemaining;
    }
}
