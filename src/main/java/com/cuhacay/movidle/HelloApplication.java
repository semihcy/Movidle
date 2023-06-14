package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.AutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
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
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getClassLoader().getResource("imdb_top_250_with_images.csv");
        File file = new File(url.getFile());

        ArrayList<Movie> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(";");
                Movie movie = new Movie(
                        row[1],
                        Integer.parseInt(row[2]),
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        row[7]
                );
                films.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        parent.setStyle("-fx-background-color: gray");

        VisualAutoCompleteTextField textField = (VisualAutoCompleteTextField) fxmlLoader.getNamespace().get("autoComplete");
        textField.setResource(films.stream().map(film -> new VisualSuggestion(film.getName(),film.getImageUrl())).toList());

        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) { launch(); }
}