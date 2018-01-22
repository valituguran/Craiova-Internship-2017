package com.ymens;

import java.sql.*;
import java.util.ArrayList;

class ArrayOfCurrencyDao {
    public static ArrayList <String> listofpairs = new ArrayList<String>();
    public static ArrayList <Double> valueofpairs = new ArrayList<Double>();
    public static boolean currencyArray() {
        String pair = new String();
        double value = new Double(1000);
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
            conn = DriverManager.getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("select * from `currencypairs` where pair=? and `values`=? ");
            pst.setString(1, pair);
            pst.setDouble(2, value);
            rs = pst.executeQuery();
            if (rs.next())
                status=true;
            for(int x=0;x<1024;x++){
                listofpairs.add(rs.getString(1));
                valueofpairs.add(rs.getDouble(2));
            }
            System.out.println("pairs:"+listofpairs+" "+"values"+valueofpairs);

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

}