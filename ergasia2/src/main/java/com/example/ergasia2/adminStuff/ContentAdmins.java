package com.example.ergasia2.adminStuff;

import com.example.ergasia2.cinemaStuff.Cinemas;
import com.example.ergasia2.cinemaStuff.Movies;
import com.example.ergasia2.mainPackage.Users;

import java.util.Scanner;

public class ContentAdmins extends Users {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void insertFilm() {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Give a Film: ");
        String film = input1.nextLine();
        input1.close();
        System.out.println("you gave " + film);

    }

    public void deleteFilm(Movies film) {
        System.out.println("film deleted");
    }

    public void assignFilmToCinema(Movies film, Cinemas cinema) {
        System.out.println("film: " + film + " assigned to " + cinema + " cinema");
    }
}
