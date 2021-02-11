package presentation.viewmodels;

import com.google.inject.Guice;
import com.google.inject.Injector;
import di.modules.ApplicationModule;
import domain.interfaces.UserPreferencesUseCase;

public class PreferencesViewModel {

    public String currentCurrency;
    public String currentCountry;
    public String currentCity;
    public float currentAllowancePercentage;

    private final UserPreferencesUseCase userPreferencesUseCase;

    public PreferencesViewModel() {
        Injector i = Guice.createInjector(new ApplicationModule());
        userPreferencesUseCase = i.getInstance(UserPreferencesUseCase.class);

        currentAllowancePercentage = userPreferencesUseCase.getUserAllowance();
        currentCity = userPreferencesUseCase.getUserCity();
        currentCountry = userPreferencesUseCase.getUserCountry();
        currentCurrency = userPreferencesUseCase.getUserCurrency();
    }

    public void updateCurrency(String text) {
        userPreferencesUseCase.setUserCurrency(text);
    }

    public void updateCountry(String text) {
        userPreferencesUseCase.setUserCountry(text);
    }

    public void updateCity(String text) {
        userPreferencesUseCase.setUserCity(text);
    }

    public void updateAllowancePercentage(Double newValue) {
        userPreferencesUseCase.setUserAllowance(newValue.floatValue());
    }
}
