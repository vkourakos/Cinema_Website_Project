package com.example.ergasia2.adminStuff;

import com.example.ergasia2.mainPackage.Users;

public class Admins extends Users {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void createUser(String username, String password){
        System.out.println("user " + username + " created");
    }
    public void updateUser(Users user, String username, String password){
        System.out.println("user updated");
    }
    public void deleteUser(Users user){
        System.out.println("user deleted");
    }
    public void searchUser(Users user){
        System.out.println("search completed");
    }
    public void viewAllUsers(){
        System.out.println("here are all the users");
    }
    public void registerAdmin(){
        System.out.println("admin registered");
    }
}
