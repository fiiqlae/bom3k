package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import data.database.*;
import domain.implementations.*;
import domain.interfaces.*;
import presentation.viewmodels.HomeViewModel;
import presentation.viewmodels.HomeViewModelImpl;

public class ApplicationModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RegisterUseCase.class).to(RegisterUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(LogInUseCase.class).to(LogInUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(ChangeUserPreferencesUseCase.class).to(ChangeUserPreferencesUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(AlterTransactionUseCase.class).to(AlterTransactionUseCaseImpl.class);
        bind(AddTransactionUseCase.class).to(AddTransactionUseCaseImpl.class);
        bind(CalculateAllowanceUseCase.class).to(CalculateAllowanceUseCaseImpl.class);
        bind(GetScheduledTransactionsUseCase.class).to(GetScheduledTransactionsUseCaseImpl.class);
        bind(GetRecentTransactionsUseCase.class).to(GetRecentTransactionsUseCaseImpl.class);

        bind(CredentialsManager.class).to(CredentialsManagerImpl.class).in(Scopes.SINGLETON);
        bind(Database.class).to(DatabaseImpl.class).in(Scopes.SINGLETON);
        bind(ConnectionManager.class).to(ConnectionManagerImpl.class).in(Scopes.SINGLETON);

        bind(HomeViewModel.class).to(HomeViewModelImpl.class).in(Scopes.SINGLETON);
    }
}
