package org.fis2021.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.fis2021.App;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button logoutButton;

    public void actionLogoutButton(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }
}
