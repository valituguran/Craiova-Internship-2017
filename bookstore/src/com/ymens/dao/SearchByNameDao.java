package com.ymens.dao;

import com.ymens.Author;
import com.ymens.Book;
import com.ymens.PrintAuthor;

import java.sql.*;
import java.util.Base64;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SearchByNameDao {
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

    public static LinkedList searchByName(String value) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Blob image = null;
        byte[] fileData = null;
        LinkedList<Book> list = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from books where name like ?");
            pst.setString(1,value+'%');
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
                Book book = new Book(name, isbn,author, price, description, encode);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
