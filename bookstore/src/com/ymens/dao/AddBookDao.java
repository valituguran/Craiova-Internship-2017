package com.ymens.dao;

import com.ymens.hibernate.Books;
import com.ymens.servlet.AddBookServlet;

import java.io.FileInputStream;
import java.io.InputStream;
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
    public int addBook(Books b, long cnp) throws SQLException {
        PreparedStatement ps = null;
        PreparedStatement pst = null;
        ResultSet rs ;
        int author_id = 0;
        boolean status = true;
        int i = 0;

        Connection conn = connect();
        try {
            ps = conn.prepareStatement("insert into `books` (name, author_id, isbn, price, description, image) values(?,?,?,?,?, ?)");
            ps.setString(1, b.getName());
            ps.setInt(2, AddAuthorDao.getIdAuthor(cnp));
            ps.setLong(3, b.getIsbn());
            ps.setDouble(4, b.getPrice());
            ps.setString(5, b.getDescription());
            InputStream inputStream = new FileInputStream(AddBookServlet.path);
            ps.setBlob(6, inputStream);
            i = ps.executeUpdate();
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
        return i;
    }
}
