package service;

import model.AppData;
import model.RegisteredUser;
import repository.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatService {
    private Connection connection = DBHandler.getConnection();

    public void sendMessage(String message) throws Exception {
        String query = "SELECT id FROM user WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, AppData.getInstance().getChatUser());

        ResultSet result = statement.executeQuery();
        int receiverUserId = 0;
        if (result.next()) {
            receiverUserId = result.getInt("id");
        }

        query = "INSERT INTO chat(sender_user_id, receiver_user_id, message) VALUES(?,?,?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, AppData.getInstance().getLoggedInUserId());
        statement.setInt(2, receiverUserId);
        statement.setString(3, message);

        statement.executeUpdate();
    }

    public ArrayList<String> getMessages() throws Exception {
        ArrayList<String> messages = new ArrayList<>();
        String query = "SELECT message FROM chat c " +
                "INNER JOIN user u ON c.receiver_user_id=u.id AND u.username=? " +
                "WHERE sender_user_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, AppData.getInstance().getChatUser());
        statement.setInt(2, AppData.getInstance().getLoggedInUserId());

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            messages.add(result.getString("message"));
        }

        return messages;
    }
}
