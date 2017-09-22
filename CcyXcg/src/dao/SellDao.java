package com.ymens;

import java.sql.*;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class SellDao {
    public static int sell(int id) {
        int i=0;
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

            pst = conn.prepareStatement("DELETE FROM `shop` WHERE id=?  and `quantity`=0");
            pst.setInt(1,id);
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
    public static void updateSell(int id,int quantity,String pairname) {
        Connection conn = null;
        int i=0;
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

            pst = conn.prepareStatement("UPDATE  `shop` SET  quantity=?  WHERE ID=? and pairname=?");
            pst.setInt(1,quantity);
            pst.setInt(2,id);
            pst.setString(3,pairname);
            pst.executeUpdate();
        }
        catch (Exception e) {
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
    public static boolean verify(int id) {
        boolean status = false;
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

            pst = conn.prepareStatement("select * from shop where id=? and quantity=0");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                status=true;

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
        return status;
    }
    public static int verifyNegativeQuantity(int id,String pairname) {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int quantity = 0;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("select `quantity` from shop where id=? and pairname=?");
            pst.setInt(1, id);
            pst.setString(2,pairname);
            rs = pst.executeQuery();
            if (rs.next()){
               quantity= rs.getInt("quantity");

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
        return quantity;
    }
}
