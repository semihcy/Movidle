package com.cuhacay.movidle;

public class Movie {
    public class  Properties{
        private String name;
        private int year;
        private String genre;
        private String origin;
        private String director;
        private String star;

        public String getName(){
            return name;
        }
        public int getYear(){
            return year;
        }
        public String getGenre(){
            return genre;
        }
        public String getOrigin(){
            return origin;
        }
        public String getDirector(){
            return director;
        }
        public String getStar(){
            return star;
        }
        public void setName(String newName) {
            name = newName;
        }
        public void setYear(int newYear) {
            year = newYear;
        }
        public void setGenre(String newGenre) {
            genre = newGenre;
        }
        public void setOrigin(String newOrigin) {
            origin = newOrigin;
        }
        public void setDirector(String newDirector) {
            director = newDirector;
        }
        public void setStar(String newStar) {
            star = newStar;
        }

    }

}
