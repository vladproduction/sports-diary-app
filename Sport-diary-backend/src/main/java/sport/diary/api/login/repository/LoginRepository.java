package sport.diary.api.login.repository;

public interface LoginRepository {
    public boolean isPresent(String login, String password);
}
