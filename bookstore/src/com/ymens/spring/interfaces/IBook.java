package com.ymens.spring.interfaces;

import com.ymens.spring.beans.Book;

import java.util.List;

public interface IBook {

    List<Book> selectBooks();

    Book getBook(int id);

    int addBook(Book book);


    int getIdAuthor(long cnp);
}
