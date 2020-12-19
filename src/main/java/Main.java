import com.google.inject.Guice;
import com.google.inject.Injector;
import commonDefenitions.TransactionKind;
import di.modules.ApplicationModule;
import domain.interfaces.AddTransactionUseCase;
import domain.interfaces.LogInUseCase;
import domain.interfaces.RegisterUseCase;
import presentation.models.TransactionPresentationModel;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class Main{

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        RegisterUseCase r = injector.getInstance(RegisterUseCase.class);
        LogInUseCase l = injector.getInstance(LogInUseCase.class);
        AddTransactionUseCase a = injector.getInstance(AddTransactionUseCase.class);
        try {
            //r.register("pepman2", "peppwd5");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Instant instant = timestamp.toInstant();
            l.logIn("pepman2", "peppwd5");
            //a.addTransaction(new TransactionPresentationModel("123", TransactionKind.SPENDING, true, String.valueOf(instant.toEpochMilli()), String.valueOf(instant.toEpochMilli()+14*24*60*60*1000), "asd", "asd", "asd", "asd", 123.123f));

        } catch (Exception e) {
            e.printStackTrace();
        }
        App.main(args);
    }
}