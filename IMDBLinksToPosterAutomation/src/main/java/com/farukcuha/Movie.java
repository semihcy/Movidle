package com.farukcuha;

public class Movie {

    String No;

    String Title;

    String Year;

    String Genre;

    String Origin;

    String Director;

    String Star;

    String imdb_link;

    public String getImdb_link() {
        return imdb_link;
    }

    public void setImdb_link(String imdb_link) {
        this.imdb_link = imdb_link;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public Movie(String no, String title, String year, String genre, String origin, String director, String star, String imdb_link) {
        this.imdb_link = imdb_link;
        No = no;
        Title = title;
        Year = year;
        Genre = genre;
        Origin = origin;
        Director = director;
        Star = star;
    }
}
