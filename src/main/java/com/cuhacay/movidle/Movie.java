package com.cuhacay.movidle;

public class Movie {
    private String name;
    private int year;
    private String genre;
    private String origin;
    private String director;
    private String star;
    private String imageUrl;

    public Movie(String name, int year, String genre, String origin, String director, String star, String imageUrl) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.origin = origin;
        this.director = director;
        this.star = star;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


