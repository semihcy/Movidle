package com.cuhacay.movidle.autocomplete.model;

public class VisualSuggestion extends Suggestion {

    private String imageUrl;

    public VisualSuggestion(String title, String imageUrl) {
        super(title);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
