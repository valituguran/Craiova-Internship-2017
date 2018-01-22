package dao;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */
public class UserDao {
    public static String name;
    public static String password;
    public static int type;
    public static String username;
    public static String email;
    public static Double balance;
    public static String currency;
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

    public static int  getID(String name,String password) {
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs;
        int id=0;
        try {
            pst = conn.prepareStatement("select * from `users` where `name`=? and `password`=? ");
            pst.setString(1, name);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {
                id=rs.getInt("ID");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }
}
