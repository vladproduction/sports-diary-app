package sport.diary.api.common.repository;

import sport.diary.api.common.config.ConfigUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepository {

    protected Connection getConnection() throws SQLException {

        String url = ConfigUtils.getConfigProperty("DB.url");
        String login = ConfigUtils.getConfigProperty("DB.login");
        String password = ConfigUtils.getConfigProperty("DB.password");
        return DriverManager.getConnection(url, login, password);
    }
}
