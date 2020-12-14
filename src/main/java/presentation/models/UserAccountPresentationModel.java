package presentation.models;

public class UserAccountPresentationModel {
    private final String username;
    private final String country;
    private final String city;
    private final String currency;

    public UserAccountPresentationModel(String username, String country, String city, String currency) {
        this.username = username;
        this.country = country;
        this.city = city;
        this.currency = currency;
    }

    public String getUsername() {
        return username;
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
