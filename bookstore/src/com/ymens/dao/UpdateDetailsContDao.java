package com.ymens.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Created by madalina.luca on 8/18/2017.
 */
public class UpdateDetailsContDao {

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
    public  int updatePassword(String pass) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update users set pass=? ");
            ps.setString(1, pass);
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
    public  int updateEmail(String email) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update users set email=? ");
            ps.setString(1, email);
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
    public  int updateRealName(String rn) throws SQLException
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("update users set real_name=? ");
            ps.setString(1, rn);
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
