package data.database.stores;

import commonDefenitions.DatabaseConfig;
import data.database.Database;
import data.database.Mappings;
import data.models.TransactionDataModel;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsStoreImpl implements TransactionsStore {

    private final Connection activeConnection;
    private final Mappings mappings;

    public TransactionsStoreImpl(Connection activeConnection) {
        this.activeConnection = activeConnection;
        this.mappings = new Mappings(activeConnection);
    }

    @Override
    public ArrayList<TransactionDataModel> selectUserTransactions(long userId) {
        ArrayList<TransactionDataModel> queryResult = new ArrayList<>();
        try {
            PreparedStatement statement =
                    activeConnection.prepareStatement(DatabaseConfig.querySelectTransactionsForUser);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                queryResult.add(mappings.toTransactionDataModel(resultSet));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return queryResult;
    }

    @Override
    public void alterTransaction(TransactionDataModel targetTransaction) {
        try {
            PreparedStatement statement = mappings.toUpdateTransactionQuery(targetTransaction);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteTransaction(TransactionDataModel transaction) {
        try {
            PreparedStatement statement = activeConnection.prepareStatement(DatabaseConfig.queryDeleteTransaction);
            statement.setLong(1, transaction.getTransactionId());
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void createTransaction(TransactionDataModel transactionDataModel) {
        try {
            PreparedStatement statement = mappings.toInsertTransactionQuery(transactionDataModel);
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
