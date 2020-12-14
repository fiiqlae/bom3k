package di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import data.database.Database;
import data.database.DatabaseImpl;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class).in(Scopes.SINGLETON);
    }
}
