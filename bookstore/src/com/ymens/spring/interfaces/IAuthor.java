package com.ymens.spring.interfaces;

import com.ymens.spring.beans.Author;


import java.util.List;

public interface IAuthor {

    List<Author> selectAuthors();

    Author getAuthor(long cnp);

    int addAuthor(Author a );

    int getId (int cnp);

    String getName(int id);

    int getIdAuthor(long cnp);

}