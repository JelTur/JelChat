package controller;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ViewController {

    public void changeScene(Event event, String sceneName) throws IOException {

        String scenePath = "../view/" + sceneName + ".fxml";
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenePath)));
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    public void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();
    }
}
