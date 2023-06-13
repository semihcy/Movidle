package com.cuhacay.movidle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        parent.setStyle("-fx-background-color: gray");

        AutoCompleteTextField textField = (AutoCompleteTextField) fxmlLoader.getNamespace().get("autoComplete");
        textField.setResource(Arrays.asList("Ahmet", "İsa", "Mehmet", "Ali Kemal", "Yunus", "Tunahan"));

        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("Fuck you!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}