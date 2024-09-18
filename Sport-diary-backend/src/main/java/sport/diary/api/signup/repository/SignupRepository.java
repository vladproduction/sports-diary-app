package sport.diary.api.signup.repository;

import sport.diary.api.signup.model.Customer;

public interface SignupRepository {
    public void create(Customer customer);
}
