package com.ymens.dao;

import java.sql.*;

/**
 * Created by madalina.luca on 8/9/2017.
 */
public class OrderDao {
    public static Connection connect() {
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
    public static int getUserId (String title){
        boolean status = false;
            Connection conn = connect();
            PreparedStatement pst = null;
            ResultSet rs = null;
            int id = 0;
            try {
                pst = conn.prepareStatement("select * from users where username=?");
                pst.setString(1, title);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        }

    public static int setOrder(Double orderTotal, int user_id)
    {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("insert into `order`(total_price, user_id) values(?, ?)");
            ps.setDouble(1, orderTotal);
            ps.setInt(2, user_id);
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
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
    public static int getOrderId (Double orderTotal) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int id = 0;
        try {
            pst = conn.prepareStatement("select * from `order` where `total_price`=?");
            pst.setDouble(1, orderTotal);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
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
            return id;
        }
    }

    public static void setOrderItem(int orderId, String title, double price) {
        PreparedStatement ps = null;
        int status = 0;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement("insert into order_item (order_id, book_id, price) values(?, ?, ?)");
            ps.setInt(1, orderId);
            ps.setInt(2, CartDao.getIdBook(title));
            ps.setDouble(3, price);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


