import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import di.modules.ApplicationModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("budget-o-matic 3000");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    public void stop() {
        Injector i = Guice.createInjector(new ApplicationModule());
        CredentialsManager credentialsManager = i.getInstance(CredentialsManager.class);
    }

}
