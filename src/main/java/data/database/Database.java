package data.database;

import data.database.stores.AccountStore;
import data.database.stores.TransactionsStore;

public interface Database extends AccountStore, TransactionsStore {
    void establishConnection();
    void closeConnection();
}
