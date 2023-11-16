package sport.diary.api.login.repository;

import sport.diary.api.common.repository.AbstractRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class LoginRepositoryImpl extends AbstractRepository implements LoginRepository {
    @Override
    public boolean isPresent(String login, String password) {

        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT count(*) FROM customers where login = ? and pass = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count==1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
