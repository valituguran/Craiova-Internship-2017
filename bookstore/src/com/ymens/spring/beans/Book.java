package com.ymens.spring.beans;

import java.util.Base64;



public class Book {
    private int id;
    private String name;
    private int authorId;
    private long isbn;
    private double price;
    private String description;
    private byte[] image;




    public Book() {
    }

    public int getAuthor_id() {
        return authorId;
    }

    public void setAuthor_id(int author_id) {
        this.authorId = author_id;
    }

    public Book(String name, int author_id, long isbn, double price, String description, byte[] image) {
        this.name = name;
        this.authorId = author_id;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getStrImage(byte[] image)
    {
        String encode = Base64.getEncoder().encodeToString(image);
        return encode;
    }



}
