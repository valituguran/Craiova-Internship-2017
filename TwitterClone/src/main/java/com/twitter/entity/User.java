package com.twitter.entity;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String adress;

    public User(int id, String firstname, String lastname, String username, String password, String email, String adress) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adress = adress;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }
}
