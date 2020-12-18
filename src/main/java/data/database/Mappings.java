package data.database;

import commonDefenitions.DatabaseConfig;
import commonDefenitions.TransactionKind;
import data.models.TransactionDataModel;
import data.models.UserAccountDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mappings {

    private final Connection activeConnection;

    public Mappings(Connection activeConnection) {
        this.activeConnection = activeConnection;
    }

    public TransactionDataModel toTransactionDataModel(ResultSet resultSet) throws SQLException {
        return (new TransactionDataModel(
                resultSet.getLong(11),
                resultSet.getLong(1),
                resultSet.getString(2),
                (resultSet.getString(3).equals("spending"))? TransactionKind.SPENDING : TransactionKind.INCOME,
                resultSet.getBoolean(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10),
                resultSet.getFloat(12)));
    }

    public UserAccountDataModel toUserAccountDataModel(ResultSet resultSet) throws SQLException {
        return new UserAccountDataModel(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getLong(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    public PreparedStatement toUpdateTransactionQuery(TransactionDataModel transactionDataModel) throws SQLException {
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
        s.setFloat(12, transactionDataModel.getAmount());
        s.setLong(13, transactionDataModel.getTransactionId());
        return s;
    }

    public PreparedStatement toInsertTransactionQuery(TransactionDataModel transactionDataModel) throws SQLException {
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
        s.setFloat(12, transactionDataModel.getAmount());
        return s;
    }

    public PreparedStatement toInsertUserQuery(UserAccountDataModel userAccountDataModel) throws SQLException {
        PreparedStatement s = activeConnection.prepareStatement(DatabaseConfig.queryInsertUser);
        s.setString(1, userAccountDataModel.getUsername());
        s.setString(2, userAccountDataModel.getPasswordHash());
        s.setLong(3, userAccountDataModel.getId());
        s.setString(4, userAccountDataModel.getCountry());
        s.setString(5, userAccountDataModel.getCity());
        s.setString(6, userAccountDataModel.getCurrency());
        return s;
    }

    public PreparedStatement toUpdateUserQuery(UserAccountDataModel userAccountDataModel) throws SQLException {
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
