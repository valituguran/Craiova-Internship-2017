package com.ymens.dao;

/**
 * Created by madalina.luca on 8/9/2017.
 */

import com.ymens.Author;
import com.ymens.Book;
import com.ymens.CartItem;
import com.ymens.PrintAuthor;

import java.sql.*;
import java.util.ArrayList;


public class CartDao {
    public static ArrayList alCartItems = new ArrayList();
    public static double dblOrderTotal;
    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "bookstore";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return conn;
        }
    }
    public static Book getBook(String name) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Book book = new Book();
        try {
                pst = conn.prepareStatement("select * from books where name=?");
                pst.setString(1, name);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String namebook = rs.getString("name");
                    int id_author = rs.getInt("author_id");
                    Author author = PrintAuthor.getDetails(id_author);
                    int isbn = rs.getInt("isbn");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    book = new Book(namebook, isbn, author, price, description, image);
                }
        } catch (SQLException e) {
                e.printStackTrace();
            }

        return book;
    }
    public static int getIdBook(String name) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try {
            pst = conn.prepareStatement("select * from books where name=?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void deleteCartItem(int iItemIndex) {
        alCartItems.remove(iItemIndex - 1);
        calculateOrderTotal();
    }

    public void updateCartItem(int iItemIndex, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        CartItem cartItem = null;
        iQuantity = Integer.parseInt(strQuantity);
        if(iQuantity>0) {
            cartItem = (CartItem)alCartItems.get(iItemIndex-1);
            dblUnitCost = cartItem.getUnitCost();
            dblTotalCost = dblUnitCost*iQuantity;
            cartItem.setQuantity(iQuantity);
            cartItem.setTotalCost(dblTotalCost);
            calculateOrderTotal();
        }
    }

    public void addCartItem(Book book, String strDescription,
                            String strUnitCost, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        CartItem cartItem = new CartItem();
        try {
            dblUnitCost = Double.parseDouble(strUnitCost);
            iQuantity = Integer.parseInt(strQuantity);
            if(iQuantity>0) {
                dblTotalCost = dblUnitCost*iQuantity;
                cartItem.setBook(book);
                cartItem.setUnitCost(dblUnitCost);
                cartItem.setQuantity(iQuantity);
                cartItem.setTotalCost(dblTotalCost);
                alCartItems.add(cartItem);
                calculateOrderTotal();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error while parsing from String to primitive types: "+nfe.getMessage());
            nfe.printStackTrace();
        }
    }

    public void addCartItem(CartItem cartItem) {
        alCartItems.add(cartItem);
    }

    public CartItem getCartItem(int iItemIndex) {
        CartItem cartItem = null;
        if(alCartItems.size()>iItemIndex) {
            cartItem = (CartItem) alCartItems.get(iItemIndex);
        }
        return cartItem;
    }

    public static ArrayList getCartItems() {
        return alCartItems;
    }
    public void setCartItems(ArrayList alCartItems) {
        this.alCartItems = alCartItems;
    }
    public double getOrderTotal() {
        return dblOrderTotal;
    }
    public void setOrderTotal(double dblOrderTotal) {
        this.dblOrderTotal = dblOrderTotal;
    }

    protected void calculateOrderTotal() {
        double dblTotal = 0;
        for(int counter=0;counter<alCartItems.size();counter++) {
            CartItem cartItem = (CartItem) alCartItems.get(counter);
            dblTotal+=cartItem.getTotalCost();
        }
        setOrderTotal(dblTotal);
    }

}
