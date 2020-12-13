package commonDefenitions;

public class DatabaseConfig {
   public static String dbUser = "bom3kadmin";
   public static String dbPassword = "s3cureaf";
   public static String dbUrl = "jdbc:postgresql://localhost:5432/bom3k";
   public static String querySelectTransactionsForUser = "SELECT * FROM transactions WHERE userId = ?";
}
