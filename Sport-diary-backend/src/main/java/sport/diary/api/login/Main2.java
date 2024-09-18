package sport.diary.api.login;

import sport.diary.api.login.repository.LoginRepository;
import sport.diary.api.login.repository.LoginRepositoryImpl;
import sport.diary.api.login.service.LoginService;
import sport.diary.api.login.service.LoginServiceImpl;
import sport.diary.api.signup.model.Customer;
import sport.diary.api.signup.repository.SignupRepository;
import sport.diary.api.signup.repository.SignupRepositoryImpl;
import sport.diary.api.signup.service.SignupService;
import sport.diary.api.signup.service.SignupServiceImpl;
import sport.diary.api.signup.validator.SignupValidator;
import sport.diary.api.signup.validator.SignupValidatorImpl;

public class Main2 {
    public static void main(String[] args) {
        LoginRepository loginRepository = new LoginRepositoryImpl();
        LoginService loginService = new LoginServiceImpl(loginRepository);
        boolean positive = loginService.isExist("log","pas");
        boolean negative = loginService.isExist("log1","pas1");
        System.out.println("positive: "+positive);
        System.out.println("negative: "+negative);
    }
}
/*login = 'log' and pass = 'pas';*/