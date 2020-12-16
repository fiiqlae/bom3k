package data.database;

import data.database.stores.AccountStore;
import data.database.stores.TransactionsStore;

import java.sql.SQLException;

public interface Database extends AccountStore, TransactionsStore {
    void initializeTables() throws SQLException;
}
