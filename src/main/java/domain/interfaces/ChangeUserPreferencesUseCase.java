package domain.interfaces;

public interface ChangeUserPreferencesUseCase {
    void setUserCountry(String country);
    void setUserCity(String city);
    void setUserCurrency(String currency);
}
