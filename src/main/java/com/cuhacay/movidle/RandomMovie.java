package com.cuhacay.movidle;


import java.util.ArrayList;
import java.util.Random;

public class RandomMovie {
    private ArrayList<Movie> movies;
    private Random random;

    public RandomMovie(ArrayList<Movie> movies){
        this.movies = movies;
        random = new Random();
    }

    public Movie selectRandomMovie (){
        int randomIndex = random.nextInt(movies.size());
        return movies.get(randomIndex);
    }
}
