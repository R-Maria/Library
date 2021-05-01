package org.fis2021;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis2021.services.FileSystemService;
import org.fis2021.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        initDirectory();
        UserService.initDatabase();
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(root);
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