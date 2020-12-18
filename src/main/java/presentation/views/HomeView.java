package presentation.views;

import commonDefenitions.TransactionKind;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.models.TransactionPresentationModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeView {

    @FXML
    private ListView<String> scheduled_payments;

    @FXML
    private Label allowance_remaining;

    @FXML
    private TextField searchbar;

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
    private TableColumn<TransactionPresentationModel, String> column_payment_method;

    @FXML public void initialize() {
        ObservableList<TransactionPresentationModel> demo = FXCollections.observableArrayList();
        demo.add(new TransactionPresentationModel("123", TransactionKind.SPENDING, false, "123", "123", "123", "1234", "123", "123", 123.123f));
        column_amount.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAmount())));
        recent_payments.setItems(demo);
    }

    public HomeView() {
        System.out.println("I exist");
    }
}
