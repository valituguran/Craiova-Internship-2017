package dao;



import java.sql.*;
import java.util.LinkedList;


public class SearchCurrencyDao {
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
    public static LinkedList list1= new LinkedList();
    public static LinkedList list2= new LinkedList();
    public void setList1 (LinkedList list1){this.list1=list1;}
    public void setList2 (LinkedList list2){this.list2=list2;}
    public static void searchPair(String pairtosearch) {
        boolean status = false;
        Connection conn = connect();
        PreparedStatement pst = null;
        ResultSet rs;
        String strpair;
        Double value;
        LinkedList  list1 = new LinkedList();
        LinkedList  list2 = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from `currencypairs` where pair like ? ");
            pst.setString(1, '%'+pairtosearch);
            rs = pst.executeQuery();
            while (rs.next()) {
                strpair = rs.getString("pair");
                value = rs.getDouble("values");
                list1.add(strpair);
                list2.add(value);
            }
            SearchCurrencyDao object = new SearchCurrencyDao();
            object.setList1(list1);
            object.setList2(list2);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}