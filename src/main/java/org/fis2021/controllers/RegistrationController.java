package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fis2021.App;
import org.fis2021.exceptions.EmptyFieldException;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.exceptions.WrongEmailException;
import org.fis2021.exceptions.WrongPhoneNumberException;
import org.fis2021.services.UserService;

import java.io.IOException;

public class RegistrationController {
    ObservableList<String> list = FXCollections.observableArrayList("Client","Admin");

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleBoxRegistration;
    @FXML
    private Label registrationMessage;

    @FXML
    public void initialize() {roleBoxRegistration.setItems(list);}

    @FXML
    public void registrationButtonAction() {
        try {
            UserService.checkFieldsRegister(usernameField.getText(), passwordField.getText(), roleBoxRegistration.getSelectionModel(),
                    nameField.getText(), phoneField.getText(), emailField.getText());
            UserService.checkPhoneNumber(phoneField.getText());
            UserService.checkEmail(emailField.getText());

            UserService.addUser(usernameField.getText(), passwordField.getText(), roleBoxRegistration.getValue(), nameField.getText(), emailField.getText(), phoneField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (EmptyFieldException | UsernameAlreadyExistsException | WrongEmailException | WrongPhoneNumberException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void backButtonAction() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }
}
