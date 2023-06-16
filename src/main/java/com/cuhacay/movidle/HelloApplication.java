package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("imdb_top_250_with_images.csv"))) {
            String line="";
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                String[] row = line.split(";");
                int year;
                try{
                    year = Integer.parseInt(row[2]);
                }catch (NumberFormatException e){
                    System.out.println("Invalid year format:" + row[2]);
                    continue;
                }
                Movie movie = new Movie(
                        row[1],
                        year,
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        row[8]
                );
                movies.add(movie);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        RandomMovie movieSelector = new RandomMovie(movies);
        Movie randomMovie = movieSelector.selectRandomMovie();
        System.out.println("Random Film: " + randomMovie.getName());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        parent.setStyle("-fx-background-color: gray");

        VisualAutoCompleteTextField textField = (VisualAutoCompleteTextField) fxmlLoader.getNamespace().get("autoComplete");
        textField.setResource(movies.stream().map(movie -> (Suggestion) new VisualSuggestion(movie.getName(),movie.getImageUrl())).toList());



        Button submitButton = (Button) fxmlLoader.getNamespace().get("submitButton");
        submitButton.setOnAction(event -> {
            String userInput = textField.getText();
            boolean userInputExists = movies.stream()
                    .map(Movie::getName)
                    .anyMatch(name -> name.equals(userInput));
            if (userInputExists) {
                System.out.println(userInput);
            } else {
                showErrorDialog("Invalid input!", "Please enter valid input.");
            }
        });



        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch();}

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
