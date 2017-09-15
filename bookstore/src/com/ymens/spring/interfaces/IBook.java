package com.ymens.spring.interfaces;

import com.ymens.spring.beans.Book;

import java.util.List;

public interface IBook {

    List<Book> selectBooks();
    Book getBook(int id);
    Book getBook(String name );
    int addBook(Book book);
    int getIdAuthor(long cnp);
    int getIdAuthor(String name);
    List<Integer> searchAuthors(String n);


    List<Book> searchbyAuthor(List list);

    List<Book> searchByName(String n);
}
