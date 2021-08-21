package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.AppData;
import model.RegisteredUser;
import service.UserService;

import java.io.IOException;

public class UserListController extends ViewController {
    @FXML
    private ListView<String> userListView;

    UserService userService = new UserService();

    public void initialize() {
        try {
            ObservableList<String> userList = FXCollections.observableArrayList();
            for (RegisteredUser user: userService.getAllUsers()) {
                userList.add(user.getUsername());
            }

            userListView.setItems(userList);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void handleUserClick(MouseEvent mouseEvent) {
        AppData.getInstance().setChatUser(userListView.getSelectionModel().getSelectedItem());
        try {
            changeScene(mouseEvent, "chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("clicked on " + userListView.getSelectionModel().getSelectedItem());
    }
}
