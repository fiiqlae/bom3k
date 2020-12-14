package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import domain.implementations.LogInUseCaseImpl;
import domain.implementations.RegisterUseCaseImpl;
import domain.interfaces.LogInUseCase;
import domain.interfaces.RegisterUseCase;

public class DomainModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RegisterUseCase.class).to(RegisterUseCaseImpl.class).in(Scopes.SINGLETON);
        bind(LogInUseCase.class).to(LogInUseCaseImpl.class).in(Scopes.SINGLETON);
    }
}
