package dao;



import java.sql.*;
import java.util.LinkedList;


public class SearchDao {
    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ccyxcgdatabase";
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

    public static LinkedList searchPair(String pairtosearch) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs;
        String strpair;
        Double value;
        LinkedList  list = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from `currencypairs` where pair like ? ");
            pst.setString(1, '%'+pairtosearch+'%');
            rs = pst.executeQuery();
            while (rs.next()) {
                strpair = rs.getString("pair");
                value = rs.getDouble("values");
                list.add(strpair);
                list.add(value);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}