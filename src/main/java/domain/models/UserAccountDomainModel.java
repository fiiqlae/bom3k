package domain.models;

public class UserAccountDomainModel {
    private final String username;
    private final long id;
    private final String country;
    private final String city;
    private final String currency;

    public UserAccountDomainModel(String username, long id, String country, String city, String currency) {
        this.username = username;
        this.id = id;
        this.country = country;
        this.city = city;
        this.currency = currency;
    }

    public String getUsername() {
        return username;
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
