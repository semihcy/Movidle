package com.cuhacay.movidle;

import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTextField extends TextField {
    List<String> resource = new ArrayList<>();

    List<String> suggestions = new ArrayList<>();

    ContextMenu contextMenu = new ContextMenu();

    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    public AutoCompleteTextField() {
        createSuggestionsMenu();
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 2) {
                suggestions = resource.stream().filter(item -> item.toLowerCase().contains(newValue.toLowerCase())).toList();
                createSuggestionsMenu();
                showContextMenu();
            } else contextMenu.hide();
        });
    }

    void showContextMenu() {
        Bounds bounds = localToScreen(getBoundsInLocal());
        double x = bounds.getMinX();
        double y = bounds.getMaxY();
        contextMenu.show(this, x, y);
    }

    void createSuggestionsMenu() {
        contextMenu.getItems().clear();
        suggestions.forEach(suggestion -> {
            MenuItem menuItem = new MenuItem(suggestion);
            menuItem.setOnAction(event -> {
                textProperty().set(menuItem.getText());
                end();
            });
            contextMenu.getItems().add(menuItem);
        });
    }
}
