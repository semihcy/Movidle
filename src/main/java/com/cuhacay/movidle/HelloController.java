package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    public HBox labels;
    @FXML
    public Button submitButton;
    @FXML
    public VisualAutoCompleteTextField autoComplete;
    @FXML
    public VBox root;

    private Movie randomMovie;

    ArrayList<Movie> movies = new ArrayList<>();
    @FXML
    private Label welcomeText;

    @FXML
    protected void onClickSubmit(){

        String userInput = autoComplete.getText();
        boolean userInputExists = movies.stream()
                .map(Movie::getName)
                .anyMatch(name -> name.equals(userInput));
        if (userInputExists) {
            System.out.println(userInput);
        } else {
            showErrorDialog("Invalid input!", "Please enter valid input.");
        }

    }
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            autoComplete.setResource(movies.stream().map(movie -> (Suggestion) new VisualSuggestion(movie.getName(),movie.getImageUrl())).toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        RandomMovie movieSelector = new RandomMovie(movies);
        randomMovie = movieSelector.selectRandomMovie();
        System.out.println("Random Film: " + randomMovie.getName());
    }
}