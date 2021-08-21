package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.NewUser;
import service.UserService;

public class RegisterController extends ViewController {

    public TextField nameField;
    public TextField usernameField;
    public PasswordField passwordField;

    UserService userService = new UserService();

    public void handleRegistration(ActionEvent actionEvent) {
        try {
            validateUserInfo();
            NewUser user = new NewUser(usernameField.getText(), passwordField.getText(), nameField.getText());

            userService.registerUser(user);

            showAlert("Success", "Registration successful, login to continue", AlertType.CONFIRMATION);
            changeScene(actionEvent, "login");
        } catch (Exception e) {
            showAlert("Registration Failed", e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void validateUserInfo() throws Exception {
        if (passwordField.getText().length() < 4) throw new Exception("Password needs to be minimum 4 character");
        if (usernameField.getText().length() < 5) throw new Exception("Username needs to be minimum 5 characters");
    }

    public void handleLoadLogin(ActionEvent actionEvent) {
        try {
            changeScene(actionEvent, "login");
        } catch (IOException e) {
            showAlert("Problem loading scene", e.getMessage(), AlertType.ERROR);
        }
    }
}
