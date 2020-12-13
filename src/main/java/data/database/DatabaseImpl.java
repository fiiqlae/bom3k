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
                queryResult.add(new TransactionDataModel(
                        userId,
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
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return queryResult;
    }

    @Override
    public void alterTransaction(TransactionDataModel targetTransaction) {

    }

    @Override
    public void deleteTransaction(TransactionDataModel transaction) {

    }

    @Override
    public void createTransaction(TransactionDataModel transactionDataModel) {

    }

    @Override
    public void createUser(UserAccountDataModel userAccount) {

    }

    @Override
    public void alterUser(UserAccountDataModel targetUserAccount) {

    }

    @Override
    public void deleteUser(UserAccountDataModel userAccount) {

    }
}
