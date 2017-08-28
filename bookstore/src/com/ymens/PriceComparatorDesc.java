package com.ymens;

import java.util.Comparator;

public class PriceComparatorDesc implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getPrice() < b.getPrice() ? 1 : a.getPrice() == b.getPrice() ? 0 : -1;
    }
}
