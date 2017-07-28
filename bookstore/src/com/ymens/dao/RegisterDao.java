package com.ymens.dao;

import java.sql.*;
/**
 * Created by madalina.luca on 7/27/2017.
 */
public class RegisterDao {
    public static int validate(String name, String pass, String email) {
        int status = 0;
        Connection conn = null;



        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "login";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        PreparedStatement ps = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            ps = conn.prepareStatement
                    ("insert into users values(?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            status = ps.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            }
        return status;
    }

    }

