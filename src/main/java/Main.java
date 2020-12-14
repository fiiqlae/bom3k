import com.google.inject.Guice;
import com.google.inject.Injector;
import commonDefenitions.TransactionKind;
import data.database.CredentialsManager;
import data.database.CredentialsManagerImpl;
import data.database.Database;
import data.models.TransactionDataModel;
import data.models.UserAccountDataModel;
import di.modules.ApplicationModule;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        CredentialsManager c = injector.getInstance(CredentialsManager.class);
        Database db = injector.getInstance(Database.class);
        try {
            //c.registerUser("pepman", "peppwd");
            UserAccountDataModel uadm = c.logIn("pepman", "peppwd");
            System.out.println(uadm.getId());
            TransactionDataModel tr = new TransactionDataModel(
                    uadm.getId(),
                    0,
                    "pepe",
                    TransactionKind.SPENDING,
                    false,
                    "0",
                    "0",
                    "pepe",
                    "pepe",
                    uadm.getUsername(),
                    uadm.getUsername()
            );
            db.deleteTransaction(tr);
            db.createTransaction(tr);
            ArrayList<TransactionDataModel> t = db.selectUserTransactions(uadm.getId());
            System.out.println("===");
            for (TransactionDataModel tdm: t) {
                System.out.println(tdm.getTransactionId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}