package sport.diary.api.signup.model;

public class Customer {

    private String login;
    private String email;
    private String pass;

    public Customer(String login, String email, String pass) {
        this.login = login;
        this.email = email;
        this.pass = pass;
    }

    public Customer() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
