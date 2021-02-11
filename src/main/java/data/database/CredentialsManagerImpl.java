package data.database;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import data.database.stores.LoginStore;
import data.database.stores.LoginStoreImpl;
import data.exceptions.UserExistsException;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;

import javax.security.auth.login.LoginException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CredentialsManagerImpl implements CredentialsManager {

    @Inject
    Database db;
    private LoginStore loginStore;


    public CredentialsManagerImpl() {
        loginStore = new LoginStoreImpl(new ConnectionManagerImpl().getActiveConnection());
    }

    @Override
    public UserAccountDataModel logIn(String userName, String password) throws LoginException, SQLException {
        String hash = hashCredentials(userName, password);
        UserAccountDataModel account = db.selectUserByPasswordHash(hash);
        if(account == null) throw new LoginException("authentication failed");
        else {
            loginStore.logIn(account.getId());
            return account;
        }
    }

    @Override
    public boolean registerUser(String userName, String password) throws UserExistsException, SQLException {
        try {
            // yeeeeeeah, big brain time
            UserAccountDataModel user = logIn(userName, password);
            if(user != null) loginStore.logOut();
            throw new UserExistsException("user is already registered");
        } catch (LoginException e) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Instant instant = timestamp.toInstant();
            long id = instant.toEpochMilli();
            db.createUser(new UserAccountDataModel(userName,
                    hashCredentials(userName, password),
                    id,
                    "Beralus",
                    "Minsk",
                    "BYN", 15.0f, 5.0f));
            return true;
        }
    }

    @Override
    public UserAccountDataModel getLastLoggedInUser() throws UserIsNotLoggedInException {
        UserAccountDataModel user = loginStore.getLastLoggedInUser();
        if(user != null) return user;
        else throw new UserIsNotLoggedInException("user is not logged in. Log in first");
    }

    @Override
    public void logOut() throws SQLException {
        loginStore.logOut();
    }

    private String hashCredentials(String userName, String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] inputBytes = ("pepemegasalt"+userName+password).getBytes();
            return new String(digest.digest(inputBytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException ex) {
            Logger lgr = Logger.getLogger(CredentialsManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }

}
