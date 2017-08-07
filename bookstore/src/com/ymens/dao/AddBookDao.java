package com.ymens.dao;

import com.ymens.Book;

import java.sql.*;

/**
 * Created by madalina.luca on 7/27/2017.
 */
public class AddBookDao {


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

    public static boolean addBook(Book b) throws SQLException {
        PreparedStatement ps = null;
        PreparedStatement pst = null;
        ResultSet rs ;
        int author_id = 0;
        boolean status = true;
        Connection conn = connect();
        try {
            pst = conn.prepareStatement("select * from authors where name=?");
                pst.setString(1, b.getAuthor().getName());
                rs = pst.executeQuery();
                if(rs.next()) {
                    author_id = rs.getInt("id");

            }
            ps = conn.prepareStatement("insert into books (name, author_id, isbn, price, description) values(?,?,?,?,?)");
            ps.setString(1, b.getNume());
            ps.setInt(2, author_id);
            ps.setInt(3, b.getIsbn());
            ps.setDouble(4, b.getPrice());
            ps.setString(5, b.getDescription());
            int i = ps.executeUpdate();
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conn.close();
        }
        return status;
    }
}
