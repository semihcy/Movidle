package com.cuhacay.movidle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        parent.setStyle("-fx-background-color: gray");

        Scene scene = new Scene(parent, 545, 565);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch();}

}
