package data.models;

public class UserAccountDataModel {
    private final String username;
    private final String passwordHash;
    private final long id;
    private final String country;
    private final String city;
    private final String currency;

    public UserAccountDataModel(String username, String passwordHash, long id, String country, String city, String currency) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.id = id;
        this.country = country;
        this.city = city;
        this.currency = currency;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCurrency() {
        return currency;
    }
}
