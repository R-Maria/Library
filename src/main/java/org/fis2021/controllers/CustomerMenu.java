package org.fis2021.controllers;
import javafx.event.ActionEvent;
import org.fis2021.App;
import java.io.IOException;
public class CustomerMenu {

    public void backButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("HomePage_Customer.fxml");
    }

    public void credentialButtonOnAction() throws IOException{
        App a = new App();
    }

    public void pastOrderButtonOnAction() throws IOException{
        App a = new App();
    }
}
