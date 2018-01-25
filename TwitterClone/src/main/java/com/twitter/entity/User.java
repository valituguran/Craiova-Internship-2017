package com.twitter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String adress;

    public User(String id, String firstname, String lastname, String username, String password, String email, String adress) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.userName = username;
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
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getUsername() {
        return userName;
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
