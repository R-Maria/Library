package org.fis2021;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis2021.services.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        initDirectory();
        OrderService.initDatabase();
        CartService.initDatabase();
        BookService.initDatabase();
        UserService.initDatabase();
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        if(fxml.equals("login.fxml") || fxml.equals("registration.fxml") || fxml.equals("Customer_menu.fxml") || fxml.equals("bookDetails.fxml") ||
                fxml.equals("orderDetails.fxml") || fxml.equals("bookDetailsAdmin.fxml")){
            stage.setScene(new Scene(root,600,400));
            stage.getScene().setRoot(root);
        } else {
            stage.setScene(new Scene(root, 615, 705));
            stage.getScene().setRoot(root);
        }
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if(!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

    public static void main(String[] args) {
        launch(args);
    }

}