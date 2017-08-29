package com.ymens;

import com.ymens.dao.RegisterDao;

import java.sql.*;

/**
 * Created by madalina.luca on 8/2/2017.
 */
public class UserType {
    public  static int admin;
    public  static int user;
    public UserType(){
        this.admin = 2;
        this.user = 1;
    }
    public Connection connect() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "bookstore";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }catch (Exception e) {
            System.out.println(e);
        }finally {
            return conn;
        }
    }
    public static String getType(String n, String p){
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        ResultSet resultSet;
        int type = 0;
        String typ = "";
        try {
            ps = conn.prepareStatement("select* from users where username=? and password=? ");
            ps.setString(1,n);
            ps.setString(2,p);
            resultSet= ps.executeQuery();
            if(resultSet.next()) {
                type = resultSet.getInt("type");
            }
            ps = conn.prepareStatement("select* from user_type where id=?");
            ps.setInt(1, type);
            resultSet = ps.executeQuery();
            boolean status = resultSet.next();
            if (status) {
                typ =  resultSet.getString("name");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return typ;
    }



}
