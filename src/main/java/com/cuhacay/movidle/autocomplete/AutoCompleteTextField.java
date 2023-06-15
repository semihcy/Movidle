package com.cuhacay.movidle.autocomplete;

import com.cuhacay.movidle.autocomplete.model.Suggestion;
import com.cuhacay.movidle.autocomplete.model.VisualSuggestion;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTextField extends TextField {
    List<Suggestion> resource = new ArrayList<>();

    List<Suggestion> suggestions = new ArrayList<>();

    ContextMenu contextMenu = new ContextMenu();

    public void setResource(List<Suggestion> resource) {
        this.resource = resource;
    }

    public AutoCompleteTextField() {
        createSuggestionsMenu();
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                suggestions = resource
                        .stream()
                        .filter(item -> item.getTitle().toLowerCase().startsWith(newValue.toLowerCase().substring(0, 1)) && item.getTitle().toLowerCase().contains(newValue.toLowerCase()))
                        .toList();
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
            MenuItem menuItem = createMenuItem(suggestion);
            menuItem.setOnAction(event -> {
                textProperty().set(suggestion.getTitle());
                end();
            });
            contextMenu.getItems().add(menuItem);
        });
    }

    MenuItem createMenuItem(Suggestion suggestion) {
        MenuItem menuItem = new MenuItem(suggestion.getTitle());
        return menuItem;
    }
}
