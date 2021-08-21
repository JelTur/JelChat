package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.ChatService;

public class ChatController {
    @FXML
    private ListView<String> chatView;

    @FXML
    private TextField messageField;

    ChatService chatService = new ChatService();

    public void initialize() {
        try {
            ObservableList<String> messageList = FXCollections.observableArrayList();

            messageList.addAll(chatService.getMessages());

            chatView.setItems(messageList);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        try {
            chatView.getItems().add(messageField.getText());
            chatService.sendMessage(messageField.getText());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
