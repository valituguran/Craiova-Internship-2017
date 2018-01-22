package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by lucian.Nicolescu on 8/23/2017.
 */
public class ParseDao {
    public static double addcurrency(String pair, Double value) {
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

            pst = conn.prepareStatement("INSERT INTO `currencypairs`(pair , `values`  ) values (?, ?)");
            pst.setString(1, pair);
            pst.setDouble(2,  value);
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
    /*.................................................................................................................*/
    public static LinkedList<String> pairs = new LinkedList<>();
    public static LinkedList<Double> values = new LinkedList<>();
    public  void setPairs ( LinkedList<String> pairs){
        this.pairs = pairs;
    }
    public void setValues ( LinkedList<Double> values){
        this.values = values;
    }
    public static void getcurrency() {
        int i = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        LinkedList<String> pairs1 = new LinkedList<>();
        LinkedList<Double> values1 = new LinkedList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("select * from `currencypairs`");
            rs = pst.executeQuery();
            while(rs.next()){
                pairs1.add(rs.getString("pair"));
                values1.add(rs.getDouble("values"));
            }
            ParseDao pd = new ParseDao();
            pd.setPairs(pairs1);
            pd.setValues(values1);
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

