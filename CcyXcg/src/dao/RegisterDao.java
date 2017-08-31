package com.ymens;

import java.sql.*;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class RegisterDao {
    public static int adduser(String name, String pass,String username,String email,int type) {
        int i = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("INSERT INTO `users`(`name` , username , password , email , `type` ) values (?, ?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, pass);
            pst.setString(4, email);
            pst.setInt(5, type);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return i;
    }
}
