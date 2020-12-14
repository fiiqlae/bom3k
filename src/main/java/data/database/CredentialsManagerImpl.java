package data.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.models.UserAccountDataModel;
import di.modules.ApplicationModule;

import javax.security.auth.login.LoginException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialsManagerImpl implements CredentialsManager {

    Database db;

    public CredentialsManagerImpl() {
        Injector injector = Guice.createInjector(new ApplicationModule());
        db = injector.getInstance(Database.class);
    }

    @Override
    public UserAccountDataModel logIn(String userName, String password) throws LoginException {
        String hash = hashCredentials(userName, password);
        UserAccountDataModel account = db.selectUserByPasswordHash(hash);
        if(account == null) throw new LoginException("authentication failed");
        else return account;
    }

    @Override
    public boolean registerUser(String userName, String password) throws UserExistsException {
        try {
            // yeeeeeeah, big brain time
            logIn(userName, password);
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
                    "BYN"));
            return true;
        }
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

    static class UserExistsException extends Throwable {
        public UserExistsException(String message) {
            super(message);
        }
    }
}
