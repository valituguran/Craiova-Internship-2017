package com.ymens.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Created by madalina.luca on 8/18/2017.
 */
public class UpdateAccountDetailsDao {

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
    public static int UpdatePassword(String pass, String user) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update `users` set password=? where username=?");
            ps.setString(1, pass);
            ps.setString(2, user);
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
    public static int UpdateEmail(String email, String user) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update users set email=? where username=?");
            ps.setString(1, email);
            ps.setString(2, user);
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
    public static int UpdateRealName(String rn, String user) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update users set real_name=? where username=?");
            ps.setString(1, rn);
            ps.setString(2, user);
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
