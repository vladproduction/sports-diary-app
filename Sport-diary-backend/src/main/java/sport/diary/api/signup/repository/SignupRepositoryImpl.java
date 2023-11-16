package sport.diary.api.signup.repository;

import sport.diary.api.common.repository.AbstractRepository;
import sport.diary.api.signup.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupRepositoryImpl extends AbstractRepository implements SignupRepository {
    @Override
    public void create(Customer customer) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("insert into customers (login,email,pass) values (?, ?, ?)");
            ps.setString(1, customer.getLogin());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPass());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
