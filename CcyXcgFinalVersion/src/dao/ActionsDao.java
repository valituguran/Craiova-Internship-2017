package dao;



import java.sql.*;
import java.util.LinkedList;


public class ActionsDao {
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
    public static LinkedList list1 = new LinkedList();//lista cu  numele perechii cumparate
    public static LinkedList list2 = new LinkedList();//lista cu valoarea perechii cumparate
    public void setList1(LinkedList list1){this.list1=list1;}
    public void setList2(LinkedList list2){this.list2=list2;}
    public static void getShopping(int id) {
        Connection conn = connect();
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        PreparedStatement pst = null;
        ResultSet rs;
        String strpair;
        Double value;
        LinkedList  list = new LinkedList();
        try {
            pst = conn.prepareStatement("select * from `shop` where id=?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()) {
                strpair = rs.getString("pairname");
                value = rs.getDouble("value");
                list1.add(strpair);
                list2.add(value);
            }
            ActionsDao object = new ActionsDao();
            object.setList1(list1);
            object.setList2(list2);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}