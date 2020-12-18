package presentation.views;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import di.modules.ApplicationModule;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentation.models.TransactionPresentationModel;
import presentation.viewmodels.HomeViewModel;
import presentation.viewmodels.HomeViewModelImpl;

public class HomeView {

    //@FXML
    //private ListView<String> scheduled_payments;

    //@FXML
    //private Label allowance_remaining;

    //@FXML
    //private TextField searchbar;

    //@FXML
    //private Button add_transaction;

    @FXML
    private TableView<TransactionPresentationModel> recent_payments;

    //@FXML
    //private TableColumn<TransactionPresentationModel, String> column_amount;

    //@FXML
    //private TableColumn<TransactionPresentationModel, String> column_sender;

    //@FXML
    //private TableColumn<TransactionPresentationModel, String> column_receiver;

    //@FXML
    //private TableColumn<TransactionPresentationModel, String> column_date;

    //@FXML
    //private TableColumn<TransactionPresentationModel, String> column_payment_method;

    private HomeViewModel viewModel;

    public HomeView() {
        //Injector i = Guice.createInjector(new ApplicationModule());
        //viewModel = i.getInstance(HomeViewModel.class);
    }

    @FXML public void initialize() {
        ObservableList<TransactionPresentationModel> t ;
        viewModel = new HomeViewModelImpl();
        t = viewModel.getRecentTransactions();
        //recent_payments.setItems(t);
    }

}
