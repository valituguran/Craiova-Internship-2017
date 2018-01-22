package com.ymens.dao;

import com.ymens.hibernate.PrintAuthor;
import com.ymens.spring.beans.Author;
import com.ymens.spring.beans.Book;

import java.sql.*;
import java.util.Base64;

/**
 * Created by madalina.luca on 8/23/2017.
 */
public class ViewBookDao {

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
    public static Book selectBook(String value) {
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        byte[] fileData = null;
        Book book = new Book();
        try {
            pst = conn.prepareStatement("select * from books where name=?");
            pst.setString(1,value);
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
                book = new Book(name, id_author, isbn, price, description, fileData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
