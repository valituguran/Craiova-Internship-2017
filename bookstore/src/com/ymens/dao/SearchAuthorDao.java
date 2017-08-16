package com.ymens.dao;

import com.ymens.Author;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SearchAuthorDao {
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

    public static LinkedList select() {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;

        LinkedList <Author> list = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from authors ");
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String nationality = rs.getString("nationality");
                String description = rs.getString("description");
                long cnp = rs.getLong("CNP");
                Author author = new Author(name, age, nationality, description, cnp);
                list.add(author);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}