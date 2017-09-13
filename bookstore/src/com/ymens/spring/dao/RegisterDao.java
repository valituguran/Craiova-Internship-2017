package com.ymens.spring.dao;


import com.ymens.hibernate.UserType;
import com.ymens.spring.AppConfig;
import com.ymens.spring.beans.User;
import com.ymens.spring.interfaces.IUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RegisterDao implements IUser{
    static DataSource dataSource = AppConfig.dataSource();
    static JdbcOperations jdbcTemplateObject = new JdbcTemplate(dataSource);


    @Override
    public int insertUser(final User u) {
        String SQL = "insert into users (username, password, real_name, email,type) values(?,?,?,?,?)";
        JdbcTemplate jdbcTemplateObject = u.getJdbcTemplate();
        return jdbcTemplateObject.update(SQL, new Object[]{u.getUsername(), u.getPassword(), u.getRealName(), u.getEmail(), UserType.user});
    }

    @Override
    public  boolean validateUser(String name, String password) {
        String sql = "select id from users where username = ? and password = ?";
        int id = (int) jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{name, password}, Integer.class);
        if (id > 0)
            return true;
        else
            return false;
    }

    @Override
    public  int getid(User user) {
        String sql = "select id from users where username = ? and password = ?";
        JdbcOperations jdbcTemplateObject = user.getJdbcTemplate();
        int id = (int) jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{user.getUsername(), user.getPassword()}, Integer.class);
        return id;
    }

    @Override
    public  int getId(String name, String password) {
        String sql = "select id from users where username = ? and password = ?";
       return jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{name, password}, Integer.class);

    }

    @Override
    public User getUser(String name, String password) {
        String sql="SELECT * FROM users WHERE name=? and password=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{name, password}, User.class);
    }
}