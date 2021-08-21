package model;

public class NewUser extends AbstractUser {
    String password;

    public NewUser(String username, String password, String name) {
        super(username, name);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
