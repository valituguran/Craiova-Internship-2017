package com.ymens.hibernate;



import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table( name = "books" )
public class Books {
    private int id;
    private String name;
    private int authorId;
    private long isbn;
    private double price;
    private String description;
    private byte[] image;
    private int author_id;
    public Books() {
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Books(String name, int author_id, long isbn, double price, String description, byte[] image) {
        this.name = name;
        this.authorId = author_id;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author_id")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "isbn")
    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (id != books.id) return false;
        if (authorId != books.authorId) return false;
        if (isbn != books.isbn) return false;
        if (Double.compare(books.price, price) != 0) return false;
        if (name != null ? !name.equals(books.name) : books.name != null) return false;
        if (description != null ? !description.equals(books.description) : books.description != null) return false;
        if (!Arrays.equals(image, books.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + authorId;
        result = 31 * result + (int) (isbn ^ (isbn >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

}
