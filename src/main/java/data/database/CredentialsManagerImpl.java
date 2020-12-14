package data.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.exceptions.UserExistsException;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;
import di.modules.DataModule;

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
    UserAccountDataModel lastLoggedIn;

    public CredentialsManagerImpl() {
        Injector injector = Guice.createInjector(new DataModule());
        db = injector.getInstance(Database.class);
    }

    @Override
    public UserAccountDataModel logIn(String userName, String password) throws LoginException {
        String hash = hashCredentials(userName, password);
        UserAccountDataModel account = db.selectUserByPasswordHash(hash);
        if(account == null) throw new LoginException("authentication failed");
        else {
            lastLoggedIn = account;
            return account;
        }
    }

    @Override
    public boolean registerUser(String userName, String password) {
        else return account;
    }

    @Override
    public boolean registerUser(String userName, String password) throws UserExistsException {
        try {
            // yeeeeeeah, big brain time
            logIn(userName, password);
            throw new UserExistsException("user is already registered");
        } catch (LoginException | UserExistsException e) {
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

    @Override
    public UserAccountDataModel getLastLoggedInUser() throws UserIsNotLoggedInException {
        if(lastLoggedIn != null) return lastLoggedIn;
        else throw new UserIsNotLoggedInException("user is not logged in. Log in first");
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
