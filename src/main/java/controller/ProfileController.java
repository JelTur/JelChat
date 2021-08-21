package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import model.AppData;
import model.NewUser;
import model.RegisteredUser;
import service.UserService;

public class ProfileController extends ViewController implements Initializable {
    public Label userNameLabel;
    public Label nameLabel;

    UserService userService = new UserService();

    public void handleLogout(ActionEvent actionEvent) {
        try {
            AppData.getInstance().setLoggedInUserId(null);
            changeScene(actionEvent, "login");
        } catch (IOException e) {
            showAlert("Problem with navigation", e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RegisteredUser user = this.userService.getUserProfile(AppData.getInstance().getLoggedInUserId());
            nameLabel.setText(user.getName());
            userNameLabel.setText(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("User not found", e.getMessage(), AlertType.ERROR);
        }
    }

}
