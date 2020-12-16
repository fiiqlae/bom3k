package data.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import commonDefenitions.DatabaseConfig;
import data.database.stores.AccountStore;
import data.database.stores.TransactionsStore;
import data.models.TransactionDataModel;
import data.models.UserAccountDataModel;
import di.modules.DataModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseImpl implements Database {

    private Connection activeConnection;
    private AccountStore accountStore;
    private TransactionsStore transactionsStore;

    public DatabaseImpl() {
        try {
            establishConnection();
            initializeTables();
            Injector injector = Guice.createInjector(new DataModule());
            accountStore = injector.getInstance(AccountStore.class);
            transactionsStore = injector.getInstance(TransactionsStore.class);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private void initializeTables() throws SQLException {
        PreparedStatement transactions = activeConnection.prepareStatement(DatabaseConfig.queryCreateTransactions);
        PreparedStatement users = activeConnection.prepareStatement(DatabaseConfig.queryCreateUsers);
        transactions.execute();
        users.execute();
    }

    @Override
    public void establishConnection() {
        try {
            activeConnection = DriverManager.getConnection(DatabaseConfig.dbUrl,
                    DatabaseConfig.dbUser,
                    DatabaseConfig.dbPassword);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void closeConnection() {
        try {
            activeConnection.close();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void createUser(UserAccountDataModel userAccount) {
        accountStore.createUser(userAccount);
    }

    @Override
    public void alterUser(UserAccountDataModel targetUserAccount) {
        accountStore.alterUser(targetUserAccount);
    }

    @Override
    public void deleteUser(UserAccountDataModel userAccount) {
        accountStore.deleteUser(userAccount);
    }

    @Override
    public UserAccountDataModel selectUserByPasswordHash(String hash) {
        return accountStore.selectUserByPasswordHash(hash);
    }

    @Override
    public ArrayList<TransactionDataModel> selectUserTransactions(long userId) {
        return transactionsStore.selectUserTransactions(userId);
    }

    @Override
    public void alterTransaction(TransactionDataModel targetTransaction) {
        transactionsStore.alterTransaction(targetTransaction);
    }

    @Override
    public void deleteTransaction(TransactionDataModel transaction) {
        transactionsStore.deleteTransaction(transaction);
    }

    @Override
    public void createTransaction(TransactionDataModel transactionDataModel) {
        transactionsStore.createTransaction(transactionDataModel);
    }
}
