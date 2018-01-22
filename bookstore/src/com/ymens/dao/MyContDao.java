package com.ymens.dao;

import com.ymens.hibernate.User;

import java.sql.*;

/**
 * Created by madalina.luca on 8/3/2017.
 */
public class MyContDao {
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

    public static User select(String n, String p) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst ;
        ResultSet rs ;
        User user = new User();

        try {
            pst = conn.prepareStatement("select * from users where username=? and password=?");
            pst.setString(1,n);
            pst.setString(2,p);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                String realname = rs.getString("real_name");
                String email = rs.getString("email");
                int type = rs.getInt("type");
                user = new User(id, name, password, realname, email, type);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}


