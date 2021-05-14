package org.fis2021.controllers;

import org.fis2021.App;

import java.io.IOException;

public class BookDetails {

    public void backButtonlOnAction() throws IOException {
        App a = new App();
        a.changeScene("HomePage_Customer.fxml");
    }
}