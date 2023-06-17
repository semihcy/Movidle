package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import com.cuhacay.movidle.square.DownArrowMagicSquare;
import com.cuhacay.movidle.square.GreenMagicSquare;
import com.cuhacay.movidle.square.RedMagicSquare;
import com.cuhacay.movidle.square.UpArrowMagicSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovidleController implements Initializable {
    @FXML
    private Label guessesLeftLabel;
    @FXML
    public HBox labels;
    @FXML
    public Button submitButton;
    @FXML
    public VisualAutoCompleteTextField autoComplete;
    @FXML
    public VBox root;

    public VBox squares;

    private Movie randomMovie;
    private int guessesLeft = 5;
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Movie> gameMovies = new ArrayList<>();
    @FXML
    protected void onClickSubmit() {
        guessesLeftLabel.setVisible(true);
        labels.setVisible(true);
        String userInput = autoComplete.getText();
        List<Movie> filterMovies = movies.stream().filter(movie -> movie.getName().equals(userInput)).toList();
        autoComplete.clear();

        if (filterMovies.isEmpty()) {
            showErrorDialog();
        } else {
            Movie guess = filterMovies.get(0);
            HBox guessProperties = new HBox();
            guessProperties.setSpacing(10);
            guessesLeft--;
            updateGuessesLeftLabel();

            if (guess.getName().equals(randomMovie.getName()))
                guessProperties.getChildren().add(GreenMagicSquare.create(guess.getName()));
            else
                guessProperties.getChildren().add(RedMagicSquare.create(guess.getName()));

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

            if (guess.getYear() > randomMovie.getYear())
                guessProperties.getChildren().add(DownArrowMagicSquare.create(String.valueOf(guess.getYear())));
            else if (guess.getYear() < randomMovie.getYear())
                guessProperties.getChildren().add(UpArrowMagicSquare.create(String.valueOf(guess.getYear())));
            else
                guessProperties.getChildren().add(GreenMagicSquare.create(String.valueOf(guess.getYear())));


            squares.getChildren().add(guessProperties);
            System.out.println(guess.getName());

            boolean isFail = false;
            for (int i = 0; i < guessProperties.getChildren().size(); i ++) {
                if (!(guessProperties.getChildren().get(i) instanceof GreenMagicSquare)) {
                    isFail = true;
                    gameMovies.remove(guess);
                    autoComplete.setResource(gameMovies.stream().map(movie -> (Suggestion) new VisualSuggestion(movie.getName(), movie.getImageUrl())).toList());
                    break;
                }
            }
            if (!isFail) {
                playSound("yeah.m4a");
                showFinishDialog("Congratulations!");
                createNewRandomMovie();
            }
            if (squares.getChildren().size() > 4) {
                playSound("you_lost_again_and_again.m4a");
                showFinishDialog("Hey loser, try again!");
            }
        }
    }
    private void updateGuessesLeftLabel() {
        guessesLeftLabel.setText("Guesses left: " + guessesLeft);
    }
    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please enter valid input!");
        alert.showAndWait();
    }

    private void showFinishDialog(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(title);
        alert.setOnCloseRequest(event -> onFinishGame());
        alert.showAndWait();
    }

    void playSound(String mediaPath) {
        Media media = new Media(new File(mediaPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void onFinishGame() {
        squares.getChildren().clear();
        labels.setVisible(false);
        autoComplete.clear();
        gameMovies = movies;
        guessesLeftLabel.setVisible(false);
        guessesLeft = 5;
    }
    private void createNewRandomMovie() {
        RandomMovie movieSelector = new RandomMovie(gameMovies);
        randomMovie = movieSelector.selectRandomMovie();
        System.out.println("New Random Film: " + randomMovie.getName());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try (BufferedReader br = new BufferedReader(new FileReader("imdb_top_250_with_images.csv"))) {
            String line = "";
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] row = line.split(";");
                int year;
                try {
                    year = Integer.parseInt(row[2]);
                } catch (NumberFormatException e) {
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
            gameMovies = movies;
            autoComplete.setResource(movies.stream().map(movie -> (Suggestion) new VisualSuggestion(movie.getName(), movie.getImageUrl())).toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        createNewRandomMovie();
    }

}