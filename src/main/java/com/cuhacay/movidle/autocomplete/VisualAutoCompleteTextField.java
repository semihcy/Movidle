package com.cuhacay.movidle.autocomplete;

import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class VisualAutoCompleteTextField extends AutoCompleteTextField {

    @Override
    MenuItem createMenuItem(Suggestion suggestion) {
        VisualSuggestion visualSuggestion = (VisualSuggestion) suggestion;
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        ImageView image = new ImageView();
        loadImageAsynchronously(image, visualSuggestion.getImageUrl());
        image.setFitWidth(50);
        image.setFitHeight(75);
        Label label = new Label(visualSuggestion.getTitle());
        hBox.getChildren().addAll(image, label);
        MenuItem menuItem = new MenuItem();
        menuItem.setGraphic(hBox);
        return menuItem;
    }

    void loadImageAsynchronously(ImageView imageView, String imageUrl) {
        String imagePlaceHolderUri = new File("images/movie_poster_image_place_holder.png").toURI().toString();
        imageView.setImage(new Image(imagePlaceHolderUri));
        Task<Image> imageLoadingTask = new Task<>() {
            @Override
            protected Image call() throws Exception {
                return new Image(imageUrl);
            }
        };

        imageLoadingTask.setOnSucceeded(event -> {
            Image loadedImage = imageLoadingTask.getValue();
            imageView.setImage(loadedImage);
        });

        imageLoadingTask.setOnFailed(event -> {
            Throwable exception = imageLoadingTask.getException();
            System.out.println(exception.getMessage());
        });

        Thread imageLoadingThread = new Thread(imageLoadingTask);
        imageLoadingThread.setDaemon(true);
        imageLoadingThread.start();
    }
}
