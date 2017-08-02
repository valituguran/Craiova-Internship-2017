package com.ymens;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class Book {
    String name;
    int isbn;
    int id_author;
    double price;
    String description;
    public Book(String name, int isbn, int id_author, double price, String description){
        this.name = name;
        this.id_author = id_author;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
    }
    public String getNume(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getIsbn (){
        return this.isbn;
    }
    public double getPrice(){
            return this.price;
    }
    public double getIdAuthor(){
        return this.id_author;
    }
}
