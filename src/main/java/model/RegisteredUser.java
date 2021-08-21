package model;

public class RegisteredUser extends AbstractUser {
    Integer id;

    public RegisteredUser(Integer id, String username, String name) {
        super(username, name);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
