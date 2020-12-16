package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import data.database.*;

import java.sql.Connection;

public class DataModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class).in(Scopes.SINGLETON);
        bind(CredentialsManager.class).to(CredentialsManagerImpl.class).in(Scopes.SINGLETON);
        bind(ConnectionManager.class).to(ConnectionManagerImpl.class).in(Scopes.SINGLETON);
    }
}
