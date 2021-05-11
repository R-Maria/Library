package org.fis2021.controllers;

import org.fis2021.App;

import java.io.IOException;

public class HomePageCustomer {
    public void logoutButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");

    }
    public void menuButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("Customer_menu.fxml");

    }
    public void orderButtonOnAction() throws IOException {
        App a = new App();

    }
}
