package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.AppData;
import model.NewUser;
import model.RegisteredUser;
import repository.DBHandler;

public class UserService {
    private Connection connection = DBHandler.getConnection();

    public int verifyUserDetails(String username, String password) throws Exception {
        String query = "SELECT id FROM user WHERE username = ? && password = ? LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        Integer userId = null;
        if (result.next()) userId = result.getInt("id");

        DBHandler.close(result, statement, connection);
        if (userId == null || userId == 0) throw new Exception("Username and password not correct");

        return userId;
    }

    public RegisteredUser getUserProfile(int userId) throws Exception {
        String query = "SELECT id, username, name FROM user WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        RegisteredUser user = null;
        if (result.next()) {
            user = new RegisteredUser(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("name")
            );
        }
        DBHandler.close(result, statement, connection);

        if (user == null || userId == 0) throw new Exception("Username and password not correct");

        return user;
    }

    public void registerUser(NewUser user) throws Exception {
        String query = "INSERT INTO user(username, password, name) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());

        statement.executeUpdate();
        DBHandler.close(statement, connection);
    }

    public ArrayList<RegisteredUser> getAllUsers() throws Exception {
        ArrayList<RegisteredUser> users = new ArrayList<>();
        String query = "SELECT id, username, name FROM user WHERE id!=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, AppData.getInstance().getLoggedInUserId());

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            users.add(new RegisteredUser(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("name"))
            );
        }

        DBHandler.close(result, statement, connection);

        return users;
    }
}
