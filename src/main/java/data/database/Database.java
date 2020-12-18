package data.database;

import data.database.stores.AccountStore;
import data.database.stores.TransactionsStore;
import data.exceptions.UserExistsException;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public interface Database extends AccountStore, TransactionsStore {
    void initializeTables() throws SQLException;
}
