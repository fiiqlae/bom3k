package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import domain.implementations.*;
import domain.interfaces.*;

public class DomainModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RegisterUseCase.class).to(RegisterUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(LogInUseCase.class).to(LogInUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(ChangeUserPreferencesUseCase.class).to(ChangeUserPreferencesUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(AlterTransactionUseCase.class).to(AlterTransactionUseCaseImpl.class);
        bind(AddTransactionUseCase.class).to(AddTransactionUseCaseImpl.class);
    }
}
