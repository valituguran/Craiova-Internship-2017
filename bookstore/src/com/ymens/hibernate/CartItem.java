package com.ymens.hibernate;

import com.ymens.spring.beans.Book;

/**
 * Created by madalina.luca on 8/9/2017.
 */
public class CartItem {
    private Book book;
    private double dblUnitCost;
    private int iQuantity;
    private double dblTotalCost;
    public CartItem(){
    }

       public Book getBook(){
           return this.book;
       }
       public void setBook(Book book){
           this.book = book;
       }
        public double getUnitCost() {
            return dblUnitCost;
        }
        public void setUnitCost(double dblUnitCost) {
            this.dblUnitCost = dblUnitCost;
        }
        public int getQuantity() {
            return iQuantity;
        }
        public void setQuantity(int quantity) {
            iQuantity = quantity;
        }
        public double getTotalCost() {
            return dblTotalCost;
        }
        public void setTotalCost(double dblTotalCost) {
            this.dblTotalCost = dblTotalCost;
        }
    }

