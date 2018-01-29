package com.twitter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String adress;

    public User(String id, String firstname, String lastname, String username, String password, String email, String adress) {
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

    public String getId() {
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

    public String showDetails(){
        return this.getFirstname() + "  " + this.getLastname() + "   " + this.getEmail() + "   " + this.getUsername() + "   " + this.getAdress() + "  " + this.getId() + "   " + this.getPassword();
     }

}
