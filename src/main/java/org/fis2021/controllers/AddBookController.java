package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.fis2021.App;
import org.fis2021.exceptions.BookAlreadyAdded;
import org.fis2021.exceptions.EmptyFieldException;
import org.fis2021.exceptions.WrongCountException;
import org.fis2021.exceptions.WrongYearException;
import org.fis2021.services.BookService;

import java.io.IOException;

public class AddBookController {
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField houseTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label addBookMessageLabel;
    @FXML
    private TextField countTextField;

    public void addBookButtonOnAction() {
        try {
            addBookMessageLabel.setText("Succes!");

            BookService.checkFieldsAddBook(titleTextField.getText(), authorTextField.getText(), houseTextField.getText(), genreTextField.getText(), yearTextField.getText(),
                    imageTextField.getText(), descriptionTextArea.getText(), countTextField.getText());
            BookService.checkCount(countTextField.getText());
            BookService.checkYear(yearTextField.getText());
            BookService.addUser(titleTextField.getText(), authorTextField.getText(), houseTextField.getText(), genreTextField.getText(), yearTextField.getText(),
                    imageTextField.getText(), descriptionTextArea.getText(), Integer.parseInt(countTextField.getText()));
        } catch (EmptyFieldException | BookAlreadyAdded | WrongCountException | WrongYearException e) {
            addBookMessageLabel.setText(e.getMessage());
        }
    }

    public void backButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("adminHomePage.fxml");
    }
}
