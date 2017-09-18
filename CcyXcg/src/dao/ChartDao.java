package dao;



import java.sql.*;
import java.util.Date;
import java.util.LinkedList;


public class ChartDao {
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
    public static LinkedList<Double> valuesearch;
    public static  String pairsearch;
    public static LinkedList<String> datasearch;
    public  void setpair(String pairsearch){this.pairsearch=pairsearch;}
    public void setValues ( LinkedList<Double> valuesearch){
        this.valuesearch = valuesearch;
    }
    public void setdate(LinkedList<String> datasearch){this.datasearch=datasearch;}
    public static void chartpair(String pair) {
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs;
        String pairs = null;
        LinkedList<Double> value = new LinkedList<>();
        LinkedList<String> date = new LinkedList<>();
        java.util.Date data = new Date();
        try {
            pst = conn.prepareStatement("select * from `hisotryvalues` where pair=?  ");
            pst.setString(1, pair);
            rs = pst.executeQuery();
            ChartDao object = new ChartDao();
            while(rs.next())
            {
               pairs=rs.getString("pair");
               value.add(rs.getDouble("value"));
               data=rs.getDate("date");
               date.add(String.valueOf(data));
            }
            object.setpair(pairs);
            object.setValues(value);
            object.setdate(date);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}