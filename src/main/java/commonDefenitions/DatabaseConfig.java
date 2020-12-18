package commonDefenitions;

public class DatabaseConfig {
   public static String dbUser = "bom3kadmin";
   public static String dbPassword = "s3cureaf";
   public static String dbUrl = "jdbc:postgresql://localhost:5432/bom3k";

   public static String querySelectTransactionsForUser = "SELECT * FROM transactions WHERE userId = ?;";
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
           "amount = ?" +
           "WHERE transactionId = ?;";

   public static String queryDeleteTransaction = "DELETE FROM transactions WHERE transactionId = ?;";
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
                   "userId," +
                   "amount" +
                   ") values (" +
                   "?,?,?,?,?,?,?,?,?,?,?,?" +
                   ");";
   public static String queryInsertUser =
           "INSERT INTO users(" +
                   "username," +
                   "passwordHash," +
                   "id," +
                   "country," +
                   "city," +
                   "currency," +
                   "allowancePercentage," +
                   "savingsPercentage" +
                   ") values (" +
                   "?,?,?,?,?,?,?,?" +
                   ");";
   public static String queryAlterUser =
           "UPDATE users WHERE " +
                   "username = ?" +
                   "passwordHash = ?" +
                   "id = ?" +
                   "country = ?" +
                   "city = ?" +
                   "currency = ?" +
                   "allowancePercentage = ?" +
                   "savingsPercentage = ?" +
                   "WHERE id = ?" +
                   ";";
   public static String queryDeleteUser = "DELETE FROM users WHERE id = ?;";
   public static String querySelectUserByHash = "SELECT * FROM users WHERE passwordHash = ?;";
   public static String querySelectUserById = "SELECT * FROM users WHERE id = ?;";
   public static String queryCreateUsers = "CREATE TABLE IF NOT EXISTS users (" +
           "username varchar(1000) not null," +
           "passwordHash varchar(1000) not null," +
           "id bigint primary key," +
           "country varchar(1000) not null," +
           "city varchar(1000) not null," +
           "currency varchar(10) not null," +
           "allowancePercentage real not null," +
           "savingsPercentage real not null" +
           ");";
   public static String queryCreateTransactions = "CREATE TABLE IF NOT EXISTS transactions (" +
           "transactionId bigint primary key," +
           "transactionName varchar(1000) not null," +
           "kind varchar(10)," +
           "isPeriodical bool not null," +
           "\"timestamp\" varchar(150) not null," +
           "dueDate varchar(150) not null," +
           "category varchar(1000) not null," +
           "\"comment\" varchar(1000) not null," +
           "senderName varchar(1000) not null," +
           "receiverName varchar(1000) not null," +
           "userId bigint not null," +
           "amount real not null" +
           ");";
   public static String queryCreateLoginHistory = "CREATE TABLE IF NOT EXISTS loginHistory (" +
           "logInId bigint primary key, " +
           "userId bigint not null);";
   public static String queryLogIn = "INSERT INTO loginHistory(logInId, userId) values (?,?);";
   public static String queryLogOut = "DELETE FROM loginHistory;";
   public static String querySelectLoggedInUser= "SELECT * FROM loginHistory;";
}
