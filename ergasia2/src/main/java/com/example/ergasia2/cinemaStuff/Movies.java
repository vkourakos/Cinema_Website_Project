package com.example.ergasia2.cinemaStuff;

public class Movies {
    private int movieId;
    private String movieTitle;
    private String movieCategory;
    private String movieDescription;

    public Movies() {}
    public Movies(int movieId, String movieTitle, String movieCategory, String movieDescription) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieCategory = movieCategory;
        this.movieDescription = movieDescription;

    }
    public int getMovieId() {
        return movieId;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public String getMovieCategory() {
        return movieCategory;
    }
    public String getMovieDescription() {
        return movieDescription;
    }
    public void setMovieId(int newFilmId) {
        this.movieId = newFilmId;
    }
    public void setMovieTitle(String newFilmTitle) {
        this.movieTitle = newFilmTitle;
    }
    public void setFovieCategory(String newFilmCategory) {
        this.movieCategory = newFilmCategory;
    }
    public void setMovieDescription(String newFilmDescription) {
        this.movieDescription = newFilmDescription;
    }
}
