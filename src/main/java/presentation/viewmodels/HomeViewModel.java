package presentation.viewmodels;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.exceptions.UserIsNotLoggedInException;
import di.modules.ApplicationModule;
import domain.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.models.TransactionPresentationModel;
import presentation.views.Dialogues;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class HomeViewModel {
    public ObservableList<TransactionPresentationModel> recentTransactions;
    public ObservableList<TransactionPresentationModel> scheduledTransactions;
    public String allowanceRemaining;


    private final GetRecentTransactionsUseCase getRecentTransactionsUseCase;
    private final CalculateAllowanceUseCase calculateAllowanceUseCase;
    private final GetScheduledTransactionsUseCase getScheduledTransactionsUseCase;
    private final GetCurrencyUseCase getCurrencyUseCase;
    private final AddTransactionUseCase addTransactionUseCase;
    private final AlterTransactionUseCase alterTransactionUseCase;
    private final AddPeriodicalTransactionUseCase addPeriodicalTransactionUseCase;
    private final Dialogues dialogues;

    public HomeViewModel() {
        Injector i = Guice.createInjector(new ApplicationModule());
        getRecentTransactionsUseCase = i.getInstance(GetRecentTransactionsUseCase.class);
        calculateAllowanceUseCase = i.getInstance(CalculateAllowanceUseCase.class);
        getScheduledTransactionsUseCase = i.getInstance(GetScheduledTransactionsUseCase.class);
        getCurrencyUseCase = i.getInstance(GetCurrencyUseCase.class);
        addTransactionUseCase = i.getInstance(AddTransactionUseCase.class);
        alterTransactionUseCase = i.getInstance(AlterTransactionUseCase.class);
        addPeriodicalTransactionUseCase = i.getInstance(AddPeriodicalTransactionUseCase.class);
        dialogues = new Dialogues(this::refreshAll);
        try {
            recentTransactions = FXCollections.observableArrayList(getRecentTransactionsUseCase.getRecentTransactions());
            scheduledTransactions = FXCollections.observableArrayList(getScheduledTransactionsUseCase.getScheduledTransactions());
            allowanceRemaining = calculateAllowanceUseCase.getAllowance();
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void getRecentTransactions() {
        try {
            ArrayList<TransactionPresentationModel> newTransactions =
                    getRecentTransactionsUseCase.getRecentTransactions();
            recentTransactions.clear();
            recentTransactions.addAll(newTransactions);
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getScheduledTransactions() {
        try {
            ArrayList<TransactionPresentationModel> newTransactions =
                    getScheduledTransactionsUseCase.getScheduledTransactions();
            newTransactions.stream().forEach(t -> System.out.println(t.getDueDate()));
            scheduledTransactions.clear();
            scheduledTransactions.addAll(newTransactions);
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
        return FXCollections.observableList(scheduledTransactions.stream().map(t -> String.format("%s %.2f on %s to %s",
                getCurrencyUseCase.getCurrency(),
                t.getAmount(),
                timestampToDate(t.getTimestamp()),
                t.getReceiverName())).collect(Collectors.toCollection(ArrayList<String>::new)));
    }

    public String getAllowanceRemaining() {
        try {
            allowanceRemaining = calculateAllowanceUseCase.getAllowance();
        } catch (UserIsNotLoggedInException e) {
            e.printStackTrace();
        }
        return allowanceRemaining;
    }

    public String getCurrency() {
        return getCurrencyUseCase.getCurrency();
    }

    private String timestampToDate(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(Long.parseLong(timestamp)), TimeZone.getDefault().toZoneId()
        ).toLocalDate().format(formatter);
    }

    public void addTransaction() {
        dialogues.showAddTransactionDialogue(addTransactionUseCase, addPeriodicalTransactionUseCase);
    }

    public Boolean refreshAll(Boolean b) {
        if(b) {
            getRecentTransactions();
            getAllowanceRemaining();
            getScheduledTransactions();
            return true;
        } else return false;
    }

    public void editTransactionByIndex(int selectedIndex) {
        dialogues.showAlterTransactionDialogue(alterTransactionUseCase, recentTransactions.get(selectedIndex), selectedIndex);
    }
}
