package com.ymens;

import dao.UserDao;

import java.sql.*;

public class myAccountDao {

    public static void getDetails(String name, String pass) {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        UserDao user = new UserDao();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("select * from users where `name`=? and password=?");
            pst.setString(1, name);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            while(rs.next()){
                UserDao.name=rs.getString("name");
                UserDao.username=rs.getString("username");
                UserDao.balance=rs.getDouble("Balance");
                UserDao.currency=rs.getString("Currency");
                UserDao.email=rs.getString("email");
            }
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

    }

}