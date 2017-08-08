package com.ymens;

import com.ymens.dao.RegisterDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by madalina.luca on 8/4/2017.
 */
public class PrintAuthor {

    public static Author getDetails(int id) {
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        ResultSet resultSet;
        Author author = new Author();
        try {
            ps = conn.prepareStatement("select* from authors where id=? ");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                author.name = resultSet.getString("name");
                author.description = resultSet.getString("description");
                author.age = resultSet.getInt("age");
                author.nationality = resultSet.getString("nationality");
                author.cnp = resultSet.getLong("CNP");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return author;
    }
}
