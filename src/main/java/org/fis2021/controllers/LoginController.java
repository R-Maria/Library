package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.fis2021.App;

import java.io.IOException;

public class LoginController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Client","Admin");

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleBox;

    @FXML
    public void initialize() {
        roleBox.setItems(roleList);
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        App a = new App();
        loginMessageLabel.setText("Invalid login. Please try again!");
        if(usernameTextField.getText().equals("test") && passwordField.getText().equals("test") && roleBox.getValue().equals("Admin")) {
            loginMessageLabel.setText("Succes");
            a.changeScene("homePage.fxml");
        }
        else if(usernameTextField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() throws IOException {
    }
}
