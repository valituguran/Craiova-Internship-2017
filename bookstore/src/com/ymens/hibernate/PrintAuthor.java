package com.ymens.hibernate;

import com.ymens.dao.RegisterDao;
import com.ymens.spring.beans.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrintAuthor {

    public PrintAuthor(int author_id) {
    }

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
                author.setName(resultSet.getString("name"));
                author.setDescription(resultSet.getString("description"));
                 int age = resultSet.getInt("age");
               String strage =""+age;
                author.setAge(age);
                author.setNationality( resultSet.getString("nationality"));
                author.setCnp( resultSet.getLong("CNP"));
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
    public static Author getDetailsAuthors(int id) {
        PreparedStatement ps = null;
        Connection conn = RegisterDao.connect();
        ResultSet resultSet;
        Author author = new Author();
        try {
            ps = conn.prepareStatement("select* from authors where id=? ");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                author.setName(resultSet.getString("name"));
                author.setDescription(resultSet.getString("description"));
                int age = resultSet.getInt("age");
                String strage =""+age;
                author.setAge(age);
                author.setNationality( resultSet.getString("nationality"));
                author.setCnp( resultSet.getLong("CNP"));
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
