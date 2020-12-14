package data.database;

import commonDefenitions.DatabaseConfig;
import commonDefenitions.TransactionKind;
import data.models.TransactionDataModel;
import data.models.UserAccountDataModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseImpl implements Database {

    private Connection activeConnection;

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
    public ArrayList<TransactionDataModel> selectUserTransactions(long userId) {
        ArrayList<TransactionDataModel> queryResult = new ArrayList<>();
        try {
            PreparedStatement statement =
                    activeConnection.prepareStatement(DatabaseConfig.querySelectTransactionsForUser);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                queryResult.add(toTransactionDataModel(resultSet));
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
            PreparedStatement statement = toUpdateTransactionQuery(targetTransaction);
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
            PreparedStatement statement = toInsertTransactionQuery(transactionDataModel);
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void createUser(UserAccountDataModel userAccount) {
        try {
            PreparedStatement statement = toInsertUserQuery(userAccount);
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void alterUser(UserAccountDataModel targetUserAccount) {
        try {
            PreparedStatement statement = toUpdateUserQuery(targetUserAccount);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteUser(UserAccountDataModel userAccount) {
        try {
            PreparedStatement statement = activeConnection.prepareStatement(DatabaseConfig.queryDeleteUser);
            statement.setLong(1, userAccount.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public UserAccountDataModel selectUserByPasswordHash(String hash) {
        UserAccountDataModel queryResult;
        try {
            PreparedStatement statement =
                    activeConnection.prepareStatement(DatabaseConfig.querySelectUserByHash);
            statement.setString(1, hash);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return toUserAccountDataModel(resultSet);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    private TransactionDataModel toTransactionDataModel(ResultSet resultSet) throws SQLException {
        return (new TransactionDataModel(
                resultSet.getLong(11),
                resultSet.getLong(10),
                resultSet.getString(1),
                (resultSet.getString(2).equals("spending"))? TransactionKind.SPENDING : TransactionKind.INCOME,
                resultSet.getBoolean(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)));
    }

    private UserAccountDataModel toUserAccountDataModel(ResultSet resultSet) throws SQLException {
        return new UserAccountDataModel(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getLong(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    private PreparedStatement toUpdateTransactionQuery(TransactionDataModel transactionDataModel) throws SQLException {
        PreparedStatement s = activeConnection.prepareStatement(DatabaseConfig.queryAlterTransaction);
        s.setLong(1, transactionDataModel.getTransactionId());
        s.setString(2, transactionDataModel.getTransactionName());
        s.setString(3, (transactionDataModel.getKind() == TransactionKind.SPENDING)? "spending" : "income");
        s.setBoolean(4, transactionDataModel.isPeriodical());
        s.setString(5, transactionDataModel.getTimestamp());
        s.setString(6, transactionDataModel.getDueDate());
        s.setString(7, transactionDataModel.getCategory());
        s.setString(8, transactionDataModel.getComment());
        s.setString(9, transactionDataModel.getSenderName());
        s.setString(10, transactionDataModel.getReceiverName());
        s.setLong(11, transactionDataModel.getUserId());
        s.setLong(12, transactionDataModel.getTransactionId());
        return s;
    }

    private PreparedStatement toInsertTransactionQuery(TransactionDataModel transactionDataModel) throws SQLException {
        PreparedStatement s = activeConnection.prepareStatement(DatabaseConfig.queryInsertTransaction);
        s.setLong(1, transactionDataModel.getTransactionId());
        s.setString(2, transactionDataModel.getTransactionName());
        s.setString(3, (transactionDataModel.getKind() == TransactionKind.SPENDING)? "spending" : "income");
        s.setBoolean(4, transactionDataModel.isPeriodical());
        s.setString(5, transactionDataModel.getTimestamp());
        s.setString(6, transactionDataModel.getDueDate());
        s.setString(7, transactionDataModel.getCategory());
        s.setString(8, transactionDataModel.getComment());
        s.setString(9, transactionDataModel.getSenderName());
        s.setString(10, transactionDataModel.getReceiverName());
        s.setLong(11, transactionDataModel.getUserId());
        return s;
    }

    private PreparedStatement toInsertUserQuery(UserAccountDataModel userAccountDataModel) throws SQLException {
        PreparedStatement s = activeConnection.prepareStatement(DatabaseConfig.queryInsertUser);
        s.setString(1, userAccountDataModel.getUsername());
        s.setString(2, userAccountDataModel.getPasswordHash());
        s.setLong(3, userAccountDataModel.getId());
        s.setString(4, userAccountDataModel.getCountry());
        s.setString(5, userAccountDataModel.getCity());
        s.setString(6, userAccountDataModel.getCurrency());
        return s;
    }

    private PreparedStatement toUpdateUserQuery(UserAccountDataModel userAccountDataModel) throws SQLException {
        PreparedStatement s = activeConnection.prepareStatement(DatabaseConfig.queryAlterUser);
        s.setString(1, userAccountDataModel.getUsername());
        s.setString(2, userAccountDataModel.getPasswordHash());
        s.setLong(3, userAccountDataModel.getId());
        s.setString(4, userAccountDataModel.getCountry());
        s.setString(5, userAccountDataModel.getCity());
        s.setString(6, userAccountDataModel.getCurrency());
        s.setLong(7, userAccountDataModel.getId());
        return s;
    }
}
