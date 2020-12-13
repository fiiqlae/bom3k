package data.database;

public interface CredentialsManager {
    boolean checkCredentials(String userName, String password);
    boolean registerUser(String userName, String password);
}
