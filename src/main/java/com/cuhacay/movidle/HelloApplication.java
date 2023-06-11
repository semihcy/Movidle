package com.cuhacay.movidle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Hello this will be  the best project ever.");
    }

    public static void main(String[] args) {
        launch();
    }
}