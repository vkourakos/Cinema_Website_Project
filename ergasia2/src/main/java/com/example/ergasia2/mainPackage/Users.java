package com.example.ergasia2.mainPackage;

public class Users {
    private String name;
    private String username;
    private String password;
    private String role;

    private byte[] salt;
    private byte[] hashedPassword;

    public Users() {}
    public Users(String Name,String Username, String Password) {
        this.name = Name;
        this.username = Username;
        this.password = Password;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getRole(){return role;}
    public String getPassword() {
        return password;
    }
    public byte[] getSalt() {
        return salt;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setSalt(byte[] newSalt) {
        this.salt = newSalt;
    }

    public void setHashedPassword(byte[] newHashedPassword) {
        this.salt = newHashedPassword;
    }

    public void login(String username, String password){
        System.out.println("user: " + username + " logged in");
    }
    public void logout(){
        System.out.println("logged out");
    }
}
