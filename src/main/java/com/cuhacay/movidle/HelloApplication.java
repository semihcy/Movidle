package com.cuhacay.movidle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public  class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        URL url = getClass().getClassLoader().getResource("imdb_top_250_with_images.csv");
        File file = new File(url.getFile());

        ArrayList<String[]> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                films.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        parent.setStyle("-fx-background-color: gray");

        AutoCompleteTextField textField = (AutoCompleteTextField) fxmlLoader.getNamespace().get("autoComplete");
        textField.setResource(Arrays.asList(String.valueOf(films)));

        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("Fuck you!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}