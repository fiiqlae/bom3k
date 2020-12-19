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
        App.main(args);
    }
}