package com.cuhacay.movidle;

import com.cuhacay.movidle.autocomplete.AutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.VisualAutoCompleteTextField;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
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

        AutoCompleteTextField textField = (VisualAutoCompleteTextField) fxmlLoader.getNamespace().get("autoComplete");
        textField.setResource(Arrays.asList(
                new VisualSuggestion(
                        "Avengers: Infinity War",
                        "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_FMjpg_UX1000_.jpg"
                ),
                new VisualSuggestion(
                        "The Dark Knight Rises",
                        "https://m.media-amazon.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_FMjpg_UX1000_.jpg"
                )
        ));

        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("Movidle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}