package data.database.stores;

import data.models.TransactionDataModel;

import java.util.ArrayList;

public interface TransactionsStore {

    ArrayList<TransactionDataModel> selectUserTransactions(long userId);
    void alterTransaction(TransactionDataModel targetTransaction);
    void deleteTransaction(TransactionDataModel transaction);
    void createTransaction(TransactionDataModel transactionDataModel);
}
