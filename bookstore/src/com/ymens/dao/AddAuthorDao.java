package com.ymens.dao;

import com.ymens.Author;

import java.sql.*;

/**
 * Created by madalina.luca on 8/4/2017.
 */
public class AddAuthorDao {
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
    public static int getIdAuthor(long cnp) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try {
            pst = conn.prepareStatement("select * from authors where CNP=?");
            pst.setLong(1,cnp);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int addAuthor(Author a){
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        int status = 0;
        try {
            ps = conn.prepareStatement("insert into authors (name, age, nationality, description, cnp) values(?,?,?,?,?)");
            ps.setString(1, a.getName());
            ps.setInt(2, a.getAge());
            ps.setString(3, a.getNationality());
            ps.setString(4, a.getDescription());
            ps.setLong(5, a.getCNP());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}
