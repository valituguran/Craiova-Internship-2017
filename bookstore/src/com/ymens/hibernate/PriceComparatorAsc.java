package com.ymens.hibernate;

import com.ymens.spring.beans.Book;

import java.util.Comparator;

/**
 * Created by madalina.luca on 8/25/2017.
 */
 public class PriceComparatorAsc implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getPrice() < b.getPrice() ? -1 : a.getPrice() == b.getPrice() ? 0 : 1;
    }
}
