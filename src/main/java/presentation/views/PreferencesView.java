package presentation.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import presentation.viewmodels.PreferencesViewModel;

public class PreferencesView {
    @FXML
    private Spinner<Double> percentage_to_allowance;

    @FXML
    private TextField currency_input;

    @FXML
    private TextField country_input;

    @FXML
    private TextField city_input;

    @FXML
    private Button set_currency;

    @FXML
    private Button set_country;

    @FXML
    private Button set_city;

    @FXML
    public void initialize() {
        percentage_to_allowance.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(5.0, 60.0, viewModel.currentAllowancePercentage));
        initFields();
        bindButtons();
    }

    private void initFields() {
        currency_input.setText(viewModel.currentCurrency);
        country_input.setText(viewModel.currentCountry);
        city_input.setText(viewModel.currentCity);
    }

    private void bindButtons() {
        set_currency.setOnAction(e ->
                viewModel.updateCurrency(currency_input.getText())
        );
        set_country.setOnAction(e ->
                viewModel.updateCountry(country_input.getText())
        );
        set_city.setOnAction(e ->
                viewModel.updateCity(city_input.getText())
        );
        percentage_to_allowance.valueProperty().addListener((observable, oldValue, newValue) -> {
                 viewModel.updateAllowancePercentage(newValue);
        });
    }

    private PreferencesViewModel viewModel;

    public PreferencesView() {
        viewModel = new PreferencesViewModel();
    }

}

