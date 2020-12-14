import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.database.CredentialsManagerImpl;
import data.database.Database;
import data.models.UserAccountDataModel;
import di.modules.ApplicationModule;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        CredentialsManager c = injector.getInstance(CredentialsManager.class);
        try {
            c.registerUser("pepman", "peppwd");
            UserAccountDataModel uadm = c.logIn("pepman", "peppwd");
            System.out.println(uadm.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}