package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import data.database.CredentialsManager;
import data.database.CredentialsManagerImpl;
import data.database.Database;
import data.database.DatabaseImpl;

public class DataModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class).in(Scopes.SINGLETON);
        bind(CredentialsManager.class).to(CredentialsManagerImpl.class).in(Scopes.SINGLETON);
    }
}
