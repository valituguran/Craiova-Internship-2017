package com.ymens;

/**
 * Created by madalina.luca on 8/2/2017.
 */
public class Author {

    int id;
    String name;
    int age;
    String nationality;
    String description;

    public Author(){}

    public Author( String name, int age, String nationality, String description){

        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getId (){
        return this.id;
    }
    public String getNationality(){
        return this.nationality;
    }
    public int getAge(){
        return this.age;
    }


}
