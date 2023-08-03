package com.example.ergasia2.cinemaStuff;

public class Cinemas {
    private int cinemaId;
    private boolean cinemaIs3D;
    private int cinemaNumberOfSeats;

    public Cinemas() {}
    public Cinemas(int cinemaId, boolean cinemaIs3D, int cinemaNumberOfSeats) {
        this.cinemaId = cinemaId;
        this.cinemaIs3D = cinemaIs3D;
        this.cinemaNumberOfSeats = cinemaNumberOfSeats;
    }
    public int getCinemaId() {
        return cinemaId;
    }
    public boolean getCinemaIs3D () {
        return cinemaIs3D;
    }
    public int getCinemaNumberOfSeats() {
        return cinemaNumberOfSeats;
    }

    public void setCinemaId(int newCinemaId) {
        this.cinemaId = newCinemaId;
    }
    public void setCinemaIs3D(String newCinemaIs3D) {
        this.cinemaIs3D = cinemaIs3D;
    }
    public void setCinemaNumberOfSeats(int newCinemaNumberOfSeats) {
        this.cinemaNumberOfSeats = newCinemaNumberOfSeats;
    }
}
