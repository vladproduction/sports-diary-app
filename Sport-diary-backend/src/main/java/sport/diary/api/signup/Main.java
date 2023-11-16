package sport.diary.api.signup;

import sport.diary.api.signup.model.Customer;
import sport.diary.api.signup.repository.SignupRepository;
import sport.diary.api.signup.repository.SignupRepositoryImpl;
import sport.diary.api.signup.service.SignupService;
import sport.diary.api.signup.service.SignupServiceImpl;
import sport.diary.api.signup.validator.SignupValidator;
import sport.diary.api.signup.validator.SignupValidatorImpl;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("log"+System.currentTimeMillis(),"log@com.com","pass0000");
        SignupValidator validator = new SignupValidatorImpl();
        SignupRepository repository = new SignupRepositoryImpl();
        SignupService service = new SignupServiceImpl(repository,validator);
        boolean result = service.register(customer);
        System.out.println(result);
    }
}
