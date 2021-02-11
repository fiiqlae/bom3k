package data.models;

public class UserAccountDataModel {
    private final String username;
    private final String passwordHash;
    private final long id;
    private String country;
    private String city;
    private String currency;
    private float allowancePercentage;
    private float savingsPercentage;

    public UserAccountDataModel(String username, String passwordHash, long id, String country, String city, String currency, float allowancePercentage, float savingsPercentage) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.id = id;
        this.country = country;
        this.city = city;
        this.currency = currency;
        this.allowancePercentage = allowancePercentage;
        this.savingsPercentage = savingsPercentage;
    }

    public float getAllowancePercentage() {
        return allowancePercentage;
    }

    public float getSavingsPercentage() {
        return savingsPercentage;
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

    public void setAllowancePercentage(float allowancePercentage) {
        this.allowancePercentage = allowancePercentage;
    }

    public void setSavingsPercentage(float savingsPercentage) {
        this.savingsPercentage = savingsPercentage;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
