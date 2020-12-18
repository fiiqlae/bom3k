package data.database.stores;

import data.models.UserAccountDataModel;

public interface AccountStore {
    void createUser(UserAccountDataModel userAccount);
    void alterUser(UserAccountDataModel targetUserAccount);
    void deleteUser(UserAccountDataModel userAccount);
    UserAccountDataModel selectUserByPasswordHash(String hash);
    UserAccountDataModel selectUserById(long userId);
}
