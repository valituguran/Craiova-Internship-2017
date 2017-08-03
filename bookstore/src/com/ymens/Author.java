package com.ymens;

import com.ymens.dao.RegisterDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by madalina.luca on 8/2/2017.
 */
public class Author {

    int id;
    String name;
    int age;
    String nationality;
    String description;

    public Author(){}

    public Author(int id, String name, int age, String nationality, String description){
        this.id = id;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.description = description;
    }

    public String getNume(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getId (){
        return this.id;
    }
    public String getNationality(){
        return this.nationality;
    }
    public int getAge(){
        return this.age;
    }

    public static Author getDetail(int id) {

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
            }
        }catch (SQLException e) {
                e.printStackTrace();
            }
        finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return author;
    }
}
