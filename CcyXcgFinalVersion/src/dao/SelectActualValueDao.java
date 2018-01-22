package dao;

import java.sql.*;
import java.util.HashMap;

public class SelectActualValueDao {
    public static HashMap<Double,String> pairsmap=new HashMap<Double, String>();
    public void setMap(HashMap<Double,String> pairsmap){this.pairsmap=pairsmap;};
    public static boolean select() {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        HashMap<Double,String> pairsmap=new HashMap<Double, String>();
        String pairname=null;
        Double value;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        SelectActualValueDao object = new SelectActualValueDao();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("select * from `currencypairs`  ");
            rs = pst.executeQuery();
            if (rs.next()) {
                pairname = rs.getString("pair");
                value = rs.getDouble("values");
                pairsmap.put(value, pairname);
            }
            object.setMap(pairsmap);
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