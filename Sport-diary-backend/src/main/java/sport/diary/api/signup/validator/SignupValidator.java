package sport.diary.api.signup.validator;

import sport.diary.api.signup.model.Customer;

public interface SignupValidator {
    public boolean isValid(Customer customer);
}
