package com.ymens.dao;

import com.ymens.hibernate.PrintAuthor;
import com.ymens.spring.beans.Author;
import com.ymens.spring.beans.Book;

import java.sql.*;
import java.util.Base64;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SelectBooksDao {
    private static int noOfRecords;
    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "bookstore";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return conn;
        }
    }
    public static LinkedList select( int offset, int noOfRecords) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Blob image = null;
        LinkedList<Book> list = new LinkedList();
        byte[] fileData;
        try {
            String query = "select * from books limit " + offset + ", " + noOfRecords;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id_author = rs.getInt("author_id");
                Author author = PrintAuthor.getDetails(id_author);
                long isbn = rs.getLong("isbn");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                fileData = rs.getBytes("image");
                String encode = Base64.getEncoder().encodeToString(fileData);
                Book book = new Book(name, id_author,isbn, price, description, fileData);
                list.add(book);
            }
            noOfRecords = list.size();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int getNoOfRecords() {
        return noOfRecords;
    }

    public static LinkedList select() {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Blob image = null;
        LinkedList<Book> list = new LinkedList();
        byte[] fileData;
        try {
            String query = "select * from books ";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id_author = rs.getInt("author_id");
                Author author = PrintAuthor.getDetails(id_author);
                long isbn = rs.getLong("isbn");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                fileData = rs.getBytes("image");
                String encode = Base64.getEncoder().encodeToString(fileData);
                Book book = new Book(name, id_author, isbn, price, description, fileData);
                list.add(book);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
