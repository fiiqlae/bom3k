package presentation.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.viewmodels.LoginViewModel;

import java.io.IOException;
import java.util.Objects;

public class LoginView {

    @FXML
    private Button login_btn;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private AnchorPane content;

    private LoginViewModel viewModel;

    public LoginView() {
        viewModel = new LoginViewModel();
    }

    @FXML
    public void initialize() {
        login_btn.setOnAction(e -> {
                    viewModel.login(login.getText(), password.getText());
                    try {
                        Stage s = new Stage();
                        s.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main_view.fxml")))));
                        s.setTitle("main");
                        s.show();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } {}
                    ((Stage) content.getScene().getWindow()).close();
                }
        );
    }
}
