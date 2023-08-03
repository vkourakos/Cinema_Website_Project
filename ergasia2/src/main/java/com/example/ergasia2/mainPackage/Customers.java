package com.example.ergasia2.mainPackage;

public class Customers extends Users {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void showAvailableFilms(){
        System.out.println("films");
    }
    public void makeReservation(String name){
        System.out.println("reservation successful");
    }
    public void viewReservation(String name){
        System.out.println("all your reservations");
    }
}
