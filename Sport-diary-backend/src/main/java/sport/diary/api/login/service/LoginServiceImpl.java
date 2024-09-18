package sport.diary.api.login.service;

import sport.diary.api.login.repository.LoginRepository;

public class LoginServiceImpl implements LoginService{

    private LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean isExist(String login, String password) {
        System.out.println("login service isExist; login="+login );
        boolean result = loginRepository.isPresent(login,password);
        System.out.println(result);
        return result;
    }
}
