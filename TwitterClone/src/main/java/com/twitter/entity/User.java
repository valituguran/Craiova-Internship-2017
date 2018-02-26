package com.twitter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

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
    private ArrayList<String> following;//persoanele pe care le urmareste un user;
    private ArrayList<String> follower; //persoanele care il urmaresc pe user-ul respectiv
    private byte[] image;

    public User(String id, String firstname, String lastname, String username, String password, String email, String adress,
                ArrayList<String> following,ArrayList<String> follower, byte[] image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adress = adress;
        this.follower = follower;
        this.following = following;
        this.image = image;
    }

    public User() {
    }

    public void addFollowingList(String id_following){
        following.add(id_following);
    }
    public void addFollowerList(String id_follower){
        follower.add(id_follower);
    }

    public ArrayList<String> getFollower() {
        return follower;
    }

    public ArrayList<String> getFollowing() {
        return following;
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

    public byte[] getImage() {
        return image;
    }

    public String showDetails(){
        return this.getFirstname() + "  " + this.getLastname() + "   " + this.getEmail() + "   " + this.getUsername() + "   " + this.getAdress() + "  " + this.getId() + "   " + this.getPassword() + "  " + this.getFollower() + " " + this.getFollowing();
     }

}
