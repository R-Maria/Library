package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fis2021.App;
import org.fis2021.exceptions.EmptyFieldException;
import org.fis2021.services.UserService;

import org.fis2021.exceptions.PasswordIncorrectException;

import java.io.IOException;

public class LoginController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Client","Admin");

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleBox;
    @FXML
    private Label noAccount;

    @FXML
    public void initialize() {
        roleBox.setItems(roleList);
    }

    public void loginButtonOnAction() {
        try {
            App a = new App();

            loginMessageLabel.setText("Invalid login. Please try again!");
            UserService.checkFieldsLogin(usernameTextField.getText(),passwordField.getText(),roleBox.getSelectionModel());
            boolean test = UserService.checkUserAlreadyExist(usernameTextField.getText(), passwordField.getText(), roleBox.getValue());

            if (test)
            {
                if(roleBox.getValue().equals("Client")) {
                    loginMessageLabel.setText("Success");
                    HomePageCustomer.setUsername(usernameTextField.getText());
                    MakeOrderController.setUsername(usernameTextField.getText());
                    a.changeScene("HomePage_Customer.fxml");
                } else {
                    loginMessageLabel.setText("Success");
                    a.changeScene("adminHomePage.fxml");
                }
            }
        } catch(PasswordIncorrectException | EmptyFieldException e) {
            loginMessageLabel.setText(e.getMessage());
        } catch (IOException ee) {
            System.out.println("Error");
        }
    }

    public void noAccountOnAction() throws IOException {
        App a = new App();
        a.changeScene("registration.fxml");
    }

    public void noAccountMouseEntered() {
        noAccount.setStyle("-fx-border-color: #149099; -fx-border-style: hidden hidden dotted hidden");
    }
    public void noAccountMouseExited() {
        noAccount.setStyle(null);
    }
}
