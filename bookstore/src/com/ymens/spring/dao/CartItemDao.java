package com.ymens.spring.dao;


import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.Cart;

import java.util.ArrayList;


public class CartItemDao {

    public static ArrayList allCartItems = new ArrayList();
    public static double dblOrderTotal;


    public void deleteCartItem(int iItemIndex) {
        allCartItems.remove(iItemIndex);
        calculateOrderTotal();
        setCartItems(allCartItems);
    }

    public void updateCartItem(int iItemIndex, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        Cart cart = null;
        iQuantity = Integer.parseInt(strQuantity);
        if(iQuantity>0) {
            cart = (Cart)allCartItems.get(iItemIndex);
            dblUnitCost = cart.getUnitCost();
            dblTotalCost = dblUnitCost*iQuantity;
            cart.setQuantity(iQuantity);
            cart.setTotalCost(dblTotalCost);
            calculateOrderTotal();
            setCartItems(allCartItems);
        }
    }
    public void addExisting(int iItemIndex, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        Cart cart = null;
        iQuantity = Integer.parseInt(strQuantity);
        if (iQuantity > 0) {
            cart = (Cart) allCartItems.get(iItemIndex);
            dblUnitCost = cart.getUnitCost();
            dblTotalCost = dblUnitCost * iQuantity;
            cart.setQuantity(iQuantity + 1);
            cart.setTotalCost(dblTotalCost);
            calculateOrderTotal();
            setCartItems(allCartItems);
        }

    }
    public void addCartItem(Book book, String strDescription,
                            String strUnitCost, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        Cart cart = new Cart();
        try {
            dblUnitCost = Double.parseDouble(strUnitCost);
            iQuantity = Integer.parseInt(strQuantity);
            if(iQuantity>0) {
                dblTotalCost = dblUnitCost*iQuantity;
                cart.setBook(book);
                cart.setUnitCost(dblUnitCost);
                cart.setQuantity(iQuantity);
                cart.setTotalCost(dblTotalCost);
                allCartItems.add(cart);
                calculateOrderTotal();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error while parsing from String to primitive types: "+nfe.getMessage());
            nfe.printStackTrace();
        }
    }
    public static int getItemBook(String title){
            Cart cartItem = new Cart();
        for(int i=0; i< allCartItems.size(); i++){
            cartItem = (Cart) allCartItems.get(i);
            if(cartItem.getBook().getName().equals(title))
                return i;
        }
        return 0;
    }
    public  void setOrderTotal(double dblOrderTotal) {
        this.dblOrderTotal = dblOrderTotal;
    }

    public static void addCartItem(Cart cart) {
        allCartItems.add(cart);
    }
    public Cart getCartItem(int iItemIndex) {
        Cart cart = null;
        if(allCartItems.size()>iItemIndex) {
            cart = (Cart) allCartItems.get(iItemIndex);
        }
        return cart;
    }
    public  ArrayList getCartItems() {
        return allCartItems;
    }
    public void setCartItems(ArrayList alCartItems) {
        this.allCartItems = alCartItems;
    }
    public static double getOrderTotal() {
        return dblOrderTotal;
    }
    protected  void calculateOrderTotal() {
        double dblTotal = 0;
        for(int counter=0;counter<allCartItems.size();counter++) {
            Cart cart = (Cart) allCartItems.get(counter);
            dblTotal+=cart.getTotalCost();
        }
        setOrderTotal(dblTotal);
    }
}


