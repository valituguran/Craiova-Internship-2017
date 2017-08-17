package com.ymens;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class Book {
    String name;
    long isbn;
    Author author;
    double price;
    String description;
    String image;
    byte [] imageData;
    public Book() {}
    public Book(String name, long isbn,Author author, double price, String description, String image){
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public Book(String name, long isbn,Author author, double price, String description, byte[] image){
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.imageData = image;
    }
    public String getNume(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public long getIsbn (){
        return this.isbn;
    }
    public double getPrice(){
            return this.price;
    }
    public String getImage(){return this.image;}
    public Author getAuthor(){return this.author;}
    public byte[] getImageData() { return this.imageData;}

}
