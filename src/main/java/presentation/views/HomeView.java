package presentation.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentation.models.TransactionPresentationModel;
import presentation.viewmodels.HomeViewModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class HomeView {

    @FXML
    private ListView<String> scheduled_payments;

    @FXML
    private Label allowance_remaining;

    @FXML
    private Button add_transaction;

    @FXML
    private TableView<TransactionPresentationModel> recent_payments;

    @FXML
    private TableColumn<TransactionPresentationModel, String> column_amount;

    @FXML
    private TableColumn<TransactionPresentationModel, String> column_sender;

    @FXML
    private TableColumn<TransactionPresentationModel, String> column_receiver;

    @FXML
    private TableColumn<TransactionPresentationModel, String> column_date;

    @FXML
    private TableColumn<TransactionPresentationModel, String> column_is_recurring;

    private final HomeViewModel viewModel;

    public HomeView() {
        viewModel = new HomeViewModel(); //FIXME
    }

    private void setRecentTransactionsCellFactories() {
        column_amount.setCellValueFactory(d ->
                new SimpleStringProperty(String.format("%s %.2f", viewModel.getCurrency(), d.getValue().getAmount())));
        column_amount.setSortable(false);
        column_sender.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getSenderName()));
        column_sender.setSortable(false);
        column_receiver.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getReceiverName()));
        column_receiver.setSortable(false);
        column_date.setCellValueFactory(d -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            return new SimpleStringProperty(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(Long.parseLong(d.getValue().getTimestamp())), TimeZone.getDefault().toZoneId()
            ).toLocalDate().format(formatter));
        });
        column_date.setSortable(false);
        column_is_recurring.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().isPeriodical()? "Yes" : "No"));
        column_is_recurring.setSortable(false);

        recent_payments.setRowFactory(tv -> {
            TableRow<TransactionPresentationModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    viewModel.editTransactionByIndex(recent_payments.getSelectionModel().getSelectedIndex());
                }
            });
            return row;
        });
    }

    private void setScheduledCellFactories() {
        scheduled_payments.setItems(viewModel.getScheduledTransactions());
    }


    private void bindViews() {
        setRecentTransactionsCellFactories();
        setScheduledCellFactories();
        allowance_remaining.setText(viewModel.getAllowanceRemaining());
        add_transaction.setOnAction(event -> {
            viewModel.addTransaction();
        });
    }

    @FXML
    public void initialize() {
        recent_payments.setItems(viewModel.recentTransactions);
        allowance_remaining.setText(viewModel.getAllowanceRemaining());
        viewModel.recentTransactions.addListener((ListChangeListener<TransactionPresentationModel>) c -> {
            recent_payments.setItems(viewModel.recentTransactions);
            allowance_remaining.setText(viewModel.getAllowanceRemaining());
            scheduled_payments.setItems(viewModel.getScheduledTransactions());
        });
        bindViews();
    }

}
