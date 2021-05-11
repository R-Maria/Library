package org.fis2021.controllers;
import javafx.event.ActionEvent;
import org.fis2021.App;
import java.io.IOException;
public class CustomerMenu {
    public void logOutButtonOnAction() throws IOException{
        App a = new App();
        a.changeScene("login.fxml");
    }

    public void backButtonOnAction() throws IOException {
        App a = new App();
    }

    public void credentialButtonOnAction() throws IOException{
        App a = new App();
    }

    public void pastOrderButtonOnAction() throws IOException{
        App a = new App();
    }
}
