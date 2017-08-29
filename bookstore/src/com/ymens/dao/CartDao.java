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
import java.util.Base64;


public class CartDao {
    public static ArrayList allCartItems = new ArrayList();
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
        Blob image;
        byte[] fileData = null;
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
                    fileData = rs.getBytes("image");
                    String encode = Base64.getEncoder().encodeToString(fileData);
                    book = new Book(namebook, isbn, author, price, description, encode);
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
    public static int getItemBook(String title){
       CartItem cartItem = new CartItem();
        for(int i=0; i< allCartItems.size(); i++){
           cartItem = (CartItem) allCartItems.get(i);
                   if(cartItem.getBook().getNume().equals(title))
                       return i;
        }
        return 0;
    }

    public void deleteCartItem(int iItemIndex) {
        allCartItems.remove(iItemIndex);
        calculateOrderTotal();
        setCartItems(allCartItems);
    }

    public void updateCartItem(int iItemIndex, String strQuantity) {
        double dblTotalCost = 0.0;
        double dblUnitCost = 0.0;
        int iQuantity = 0;
        CartItem cartItem = null;
        iQuantity = Integer.parseInt(strQuantity);
        if(iQuantity>0) {
            cartItem = (CartItem)allCartItems.get(iItemIndex);
            dblUnitCost = cartItem.getUnitCost();
            dblTotalCost = dblUnitCost*iQuantity;
            cartItem.setQuantity(iQuantity);
            cartItem.setTotalCost(dblTotalCost);
            calculateOrderTotal();
            setCartItems(allCartItems);
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
                allCartItems.add(cartItem);
                calculateOrderTotal();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error while parsing from String to primitive types: "+nfe.getMessage());
            nfe.printStackTrace();
        }
    }
    public  void setOrderTotal(double dblOrderTotal) {
        this.dblOrderTotal = dblOrderTotal;
    }

    public static void addCartItem(CartItem cartItem) {
        allCartItems.add(cartItem);
    }
    public CartItem getCartItem(int iItemIndex) {
        CartItem cartItem = null;
        if(allCartItems.size()>iItemIndex) {
            cartItem = (CartItem) allCartItems.get(iItemIndex);
        }
        return cartItem;
    }
    public static ArrayList getCartItems() {
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
            CartItem cartItem = (CartItem) allCartItems.get(counter);
            dblTotal+=cartItem.getTotalCost();
        }
        setOrderTotal(dblTotal);
    }
}
