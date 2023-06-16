package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import com.cuhacay.movidle.square.GreenMagicSquare;
import com.cuhacay.movidle.square.RedMagicSquare;
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
import java.util.List;
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
        labels.setVisible(true);
        String userInput = autoComplete.getText();
        List<Movie> filterMovies = movies.stream().filter(movie -> movie.getName().equals(userInput)).toList();

        if (filterMovies.isEmpty()) {
            showErrorDialog("Invalid input!", "Please enter valid input.");
        } else {
            Movie guess = filterMovies.get(0);
            HBox guessProperties = new HBox();
            guessProperties.setSpacing(10);

                if (guess.getName().equals(randomMovie.getName()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(guess.getName()));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(guess.getName()));
                if (guess.getYear() == (randomMovie.getYear()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(String.valueOf(guess.getYear())));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(String.valueOf(guess.getYear())));
                if (guess.getGenre().equals(randomMovie.getGenre()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(guess.getGenre()));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(guess.getGenre()));
                if (guess.getOrigin().equals(randomMovie.getOrigin()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(guess.getOrigin()));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(guess.getOrigin()));
                if (guess.getDirector().equals(randomMovie.getDirector()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(guess.getDirector()));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(guess.getDirector()));
                if (guess.getStar().equals(randomMovie.getStar()))
                    guessProperties.getChildren().add(GreenMagicSquare.create(guess.getStar()));
                else
                    guessProperties.getChildren().add(RedMagicSquare.create(guess.getStar()));



            root.getChildren().add(guessProperties);
            System.out.println(guess.getName());
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