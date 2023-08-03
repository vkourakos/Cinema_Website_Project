package com.example.ergasia2.cinemaStuff;

public class Provoles {
    private int provoliID, provoliNumberOfReservations;
    private Movies provoliMovie;
    private Cinemas provoliCinema;
    private String provoliStartDate, provoliEndDate;
    private boolean provoliIsAvailable;

    public Provoles() {}
    public Provoles(int provoliID, Movies provoliMovie, Cinemas provoliCinema, String provoliStartDate, String provoliEndDate, int provoliNumberOfReservations, boolean provoliIsAvailable) {
        this.provoliID = provoliID;
        this.provoliMovie = provoliMovie;
        this.provoliCinema = provoliCinema;
        this.provoliStartDate = provoliStartDate;
        this.provoliEndDate = provoliEndDate;
        this.provoliNumberOfReservations = provoliNumberOfReservations;
        this.provoliIsAvailable = provoliIsAvailable;
    }
    public int getProvoliID() {
        return provoliID;
    }
    public int getProvoliNumberOfReservations() {
        return provoliNumberOfReservations;
    }
    public Movies getProvoliMovie() {
        return provoliMovie;
    }
    public Cinemas getProvoliCinema() {
        return provoliCinema;
    }
    public String getProvoliStartDate() {
        return provoliStartDate;
    }
    public String getProvoliEndDate() {
        return provoliEndDate;
    }
    public boolean getProvoliIsAvailable() {
        return provoliIsAvailable;
    }
    public void setProvoliID(int newProvoliId) {
        this.provoliID = newProvoliId;
    }
    public void setProvoliFilm(Movies newProvoliMovie) {
        this.provoliMovie = newProvoliMovie;
    }
    public void setProvoliCinema(Cinemas newProvoliCinema) {
        this.provoliCinema = newProvoliCinema;
    }
    public void setProvoliStartDate(String newProvoliStartDate) {
        this.provoliStartDate = newProvoliStartDate;
    }
    public void setProvoliEndDate(String newProvoliEndDate) {
        this.provoliEndDate = newProvoliEndDate;
    }
    public void setProvoliIsAvailable(boolean newProvoliIsAvailable) {
        this.provoliIsAvailable = newProvoliIsAvailable;
    }
    public void setProvoliNumberOfReservations(int newProvoliNumberOfReservations) {
        this.provoliNumberOfReservations = newProvoliNumberOfReservations;
    }
}
