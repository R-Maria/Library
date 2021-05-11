package org.fis2021.controllers;

import org.fis2021.App;
import java.io.IOException;

public class AdminHomePageController {
    public void addStockButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("addBook.fxml");
    }

    public void logOutButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }
}
