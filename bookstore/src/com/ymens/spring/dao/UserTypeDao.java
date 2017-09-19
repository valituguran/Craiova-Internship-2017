package com.ymens.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserTypeDao {

    public static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public int getIdType(String n, String p){
        String sql="SELECT type FROM users WHERE username=? and password=?";
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{n, p},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

    public String getType(String n, String p){
        int id = getIdType(n, p);
        String sql="SELECT name FROM user_type WHERE id=?";
        String typ;
        try {
            typ = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, String.class);
        } catch(EmptyResultDataAccessException e){
            typ = "";
            e.printStackTrace();
        }
        return typ;
    }

    public int getIdUser(){
        String sql="SELECT id FROM user_type WHERE name=?";
        int typ;
        try {
            typ = jdbcTemplateObject.queryForObject(sql, new Object[]{"user"}, Integer.class);
        } catch(EmptyResultDataAccessException e){
            typ = 0;
            e.printStackTrace();
        }
        return typ;
    }
}
