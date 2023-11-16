package sport.diary.api.signup.service;

import sport.diary.api.signup.model.Customer;
import sport.diary.api.signup.repository.SignupRepository;
import sport.diary.api.signup.validator.SignupValidator;

public class SignupServiceImpl implements SignupService{

    private SignupRepository signupRepository;
    private SignupValidator signupValidator;


    public SignupServiceImpl(SignupRepository signupRepository, SignupValidator signupValidator) {
        this.signupRepository = signupRepository;
        this.signupValidator = signupValidator;
    }
    @Override
    public boolean register(Customer customer) {
        try{
            if(signupValidator.isValid(customer)){
                signupRepository.create(customer);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
