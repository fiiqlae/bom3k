import com.google.inject.Guice;
import com.google.inject.Injector;
import di.modules.DomainModule;
import domain.interfaces.RegisterUseCase;

public class Main{

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DomainModule());
        RegisterUseCase r = injector.getInstance(RegisterUseCase.class);
        try {
            r.register("pepman", "peppwd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}