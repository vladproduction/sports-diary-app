package sport.diary.api.signup.validator;

import sport.diary.api.signup.model.Customer;

public class SignupValidatorImpl implements SignupValidator{
    @Override
    public boolean isValid(Customer customer) {
        if(customer==null){
            return false;
        }
        String login = customer.getLogin();
        if(isEmpty(login)){
            return false;
        }
        String email = customer.getEmail();
        if(isEmpty(email)){
            return false;
        }
        String pass = customer.getPass();
        if(isEmpty(pass)){
            return false;
        }
        return true;
    }

    private boolean isEmpty(String value){
        if(value==null || value.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
