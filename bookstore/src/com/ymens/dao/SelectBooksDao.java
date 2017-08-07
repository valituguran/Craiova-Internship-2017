package com.ymens.dao;

import com.ymens.Author;
import com.ymens.Book;
import com.ymens.PrintAuthor;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SelectBooksDao {
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

    public static LinkedList select() {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;

        LinkedList<Book> list = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from books");
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id_author = rs.getInt("author_id");
                Author author = PrintAuthor.getDetails(id_author);
                int isbn = rs.getInt("isbn");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Book book = new Book(name, isbn,author, price, description);
                list.add(book);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
