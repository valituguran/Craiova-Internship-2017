package com.ymens.dao;

import java.sql.*;

/**
 * Created by madalina.luca on 7/27/2017.
 */
public class RegisterDao {
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
    public static int getType(){
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
         ResultSet user_type;
        int type = 0;
        try {
            ps = conn.prepareStatement("select* from user_type where name=? ");
            ps.setString(1,"user");
            user_type = ps.executeQuery();
            user_type.next();
            type = user_type.getInt("id");
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
        return type;
    }
    public static int validate(String name, String pass, String realname, String email) throws SQLException {

        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("insert into users (username, password, real_name, email,type) values(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, pass);
            ps.setString(3, realname);
            ps.setString(4, email);

            ps.setInt(5, getType());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conn.close();
        }
        return status;
    }
}


