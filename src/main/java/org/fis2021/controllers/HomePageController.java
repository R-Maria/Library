package org.fis2021.controllers;

import org.fis2021.App;

import java.io.IOException;

public class HomePageController {

    public void actionLogoutButton() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }
}
