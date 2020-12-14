package data.database;

import data.models.TransactionDataModel;
import data.models.UserAccountDataModel;

import java.util.ArrayList;

public interface Database {
    void establishConnection();
    void closeConnection();

    ArrayList<TransactionDataModel> selectUserTransactions(long userId);
    void alterTransaction(TransactionDataModel targetTransaction);
    void deleteTransaction(TransactionDataModel transaction);
    void createTransaction(TransactionDataModel transactionDataModel);

    void createUser(UserAccountDataModel userAccount);
    void alterUser(UserAccountDataModel targetUserAccount);
    void deleteUser(UserAccountDataModel userAccount);
    UserAccountDataModel selectUserByPasswordHash(String hash);
}
