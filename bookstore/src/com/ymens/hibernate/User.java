package com.ymens.hibernate;

/**
 * Created by madalina.luca on 8/3/2017.
 */
public class User {

    public int id;
    public String username;
    public String password;
    public String realname;
    public String email;
    public int type;

    public User() {}
    public User (int id, String username, String password, String realname, String email, int type){
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.type = type;
    }
    public int getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {return this.password; }
    public String getRealname() {
        return this.realname;
    }
    public String getEmail() {
        return this.email;
    }
    public int getType() {
        return this.type;
    }

}
