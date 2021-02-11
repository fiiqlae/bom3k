import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.exceptions.UserIsNotLoggedInException;
import di.modules.ApplicationModule;
import domain.interfaces.AddTransactionUseCase;
import domain.interfaces.LogInUseCase;
import domain.interfaces.RegisterUseCase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    AddTransactionUseCase a;
    Injector injector;
    RegisterUseCase r;
    CredentialsManager cm;
    LogInUseCase l;

    public App() {
        injector = Guice.createInjector(new ApplicationModule());
        r = injector.getInstance(RegisterUseCase.class);
        l = injector.getInstance(LogInUseCase.class);
        cm = injector.getInstance(CredentialsManager.class);
        a = injector.getInstance(AddTransactionUseCase.class);
    }

    private void login(Stage primaryStage) throws IOException {
        try {
            cm.getLastLoggedInUser();
            Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("budget-o-matic 3000");
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (UserIsNotLoggedInException e) {
            Stage s = new Stage();
            s.setScene(new Scene(FXMLLoader.load(getClass().getResource("login_view.fxml"))));
            s.setTitle("login");
            s.show();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        login(primaryStage);
    }

    @Override
    public void stop() {
        try {
            cm.logOut();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}