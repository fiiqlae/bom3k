package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import data.database.CredentialsManager;
import data.database.CredentialsManagerImpl;
import data.database.Database;
import data.database.DatabaseImpl;
import data.database.stores.AccountStore;
import data.database.stores.AccountStoreImpl;
import data.database.stores.TransactionsStore;
import data.database.stores.TransactionsStoreImpl;

public class DataModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class).in(Scopes.SINGLETON);
        bind(CredentialsManager.class).to(CredentialsManagerImpl.class).in(Scopes.SINGLETON);

        bind(TransactionsStore.class).to(TransactionsStoreImpl.class).in(Scopes.SINGLETON);
        bind(AccountStore.class).to(AccountStoreImpl.class).in(Scopes.SINGLETON);
    }
}
