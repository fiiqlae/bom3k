package domain.interfaces;

public interface UserPreferencesUseCase {
    void setUserCountry(String country);
    void setUserCity(String city);
    void setUserCurrency(String currency);
    void setUserAllowance(float allowance);

    String getUserCountry();
    String getUserCity();
    String getUserCurrency();
    float getUserAllowance();
}
