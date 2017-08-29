package com.ymens.dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by madalina.luca on 8/29/2017.
 */
public class History {
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
    public static ArrayList getOrders(String name){
        int id = getUserId(name);
        ArrayList list = getOrdersId(id);
        return list;
    }
    public static double getTotalPrice(String object_id){
        int order_id;

        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        ResultSet resultSet;
      double price = 0;
        try {
            order_id = Integer.parseInt(object_id);
            ps = conn.prepareStatement("select* from `order` where id=? ");
            ps.setInt(1,order_id);
            resultSet= ps.executeQuery();
            if(resultSet.next()) {
                price = resultSet.getDouble("total_price");
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
        return price;
    }
    private static int getUserId (String name ){
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        ResultSet resultSet;
        int id = 0;

        try {
            ps = conn.prepareStatement("select* from users where username=? ");
            ps.setString(1,name);
            resultSet= ps.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getInt("id");
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
        return id;
    }
    private static ArrayList getOrdersId (int user_id) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        int id = 0;
        try {
            pst = conn.prepareStatement("select * from `order` where `user_id`=?");
            pst.setInt(1, user_id);
            rs = pst.executeQuery();
           while (rs.next()) {
                id = rs.getInt("id");
                list.add(id);
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
            return list;
        }
    }

    public static ArrayList<String> getOrderItems (String object_id){
        int order_id;
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        int item = 0;
        try {
            order_id = Integer.parseInt(object_id);
            pst = conn.prepareStatement("select * from `order_item` where `order_id`=?");
            pst.setDouble(1, order_id);
            rs = pst.executeQuery();
           while (rs.next()) {
                item = rs.getInt("id");
                list.add(item);
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        catch (Exception e) {
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
            return list;
        }
    }
    public static int getIdBook(String object_id){
        boolean status = false;
        int order_id;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
       int book_id = 0;
        try {
            order_id = Integer.parseInt(object_id);
            pst = conn.prepareStatement("select * from order_item where id=?");
            pst.setInt(1, order_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                book_id = rs.getInt("book_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book_id;
    }

    public static String getBook(int id){

        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String title = "";
        try {
            pst = conn.prepareStatement("select * from books where id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                title = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

}
