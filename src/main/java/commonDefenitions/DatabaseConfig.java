package commonDefenitions;

public class DatabaseConfig {
   public static String dbUser = "bom3kadmin";
   public static String dbPassword = "s3cureaf";
   public static String dbUrl = "jdbc:postgresql://localhost:5432/bom3k";

   public static String querySelectTransactionsForUser = "SELECT * FROM transactions WHERE userId = ?";
   public static String queryAlterTransaction = "UPDATE transactions " +
           "SET " +
           "transactionId = ? " +
           "transactionName = ? " +
           "kind = ? " +
           "isPeriodical = ? " +
           "timestamp = ? " +
           "dueDate = ? " +
           "category = ? " +
           "comment = ? " +
           "senderName = ? " +
           "receiverName = ? " +
           "userId = ?" +
           "WHERE transactionId = ?";

   public static String queryDeleteTransaction = "DELETE FROM transactions WHERE transactionId = ?";
   public static String queryInsertTransaction =
           "INSERT INTO transactions(" +
                   "transactionId," +
                   "transactionName," +
                   "kind," +
                   "isPeriodical," +
                   "timestamp," +
                   "dueDate," +
                   "category," +
                   "comment," +
                   "senderName," +
                   "receiverName," +
                   "userId" +
                   ") values (" +
                   "?,?,?,?,?,?,?,?,?,?,?" +
                   ")";
   public static String queryInsertUser =
           "INSERT INTO users(" +
                   "username," +
                   "passwordHash," +
                   "id," +
                   "country," +
                   "city," +
                   "currency" +
                   ") values (" +
                   "?,?,?,?,?,?" +
                   ")";
   public static String queryAlterUser =
           "UPDATE users WHERE " +
                   "username = ?" +
                   "passwordHash = ?" +
                   "id = ?" +
                   "country = ?" +
                   "city = ?" +
                   "currency = ?" +
                   "WHERE id = ?";
   public static String queryDeleteUser = "DELETE FROM users WHERE id = ?";
}
