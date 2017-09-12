package com.ymens.spring.dao;


import com.ymens.hibernate.UserType;
import com.ymens.spring.beans.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RegisterDao {


    public int insertUser(final User u) {
        String SQL = "insert into users (username, password, real_name, email,type) values(?,?,?,?,?)";
        JdbcTemplate jdbcTemplateObject = u.getJdbcTemplate();
        return jdbcTemplateObject.update(SQL, new Object[]{u.getUsername(), u.getPassword(), u.getRealName(), u.getEmail(), UserType.user});
    }

    public static boolean validateUser(String name, String password, DataSource ds) {
        String sql = "select id from users where username = ? and password = ?";
        JdbcOperations jdbcTemplateObject = new JdbcTemplate(ds);
        int id = (int) jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{name, password}, Integer.class);
        if (id > 0)
            return true;
        else
            return false;
    }

    public static int getid(User user) {
        String sql = "select id from users where username = ? and password = ?";
        JdbcOperations jdbcTemplateObject = user.getJdbcTemplate();
        int id = (int) jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{user.getUsername(), user.getPassword()}, Integer.class);
        return id;
    }

    public static int getId(String name, String password, DataSource ds) {
        String sql = "select id from users where username = ? and password = ?";
        JdbcOperations jdbcTemplateObject = new JdbcTemplate(ds);
        int id = (int) jdbcTemplateObject.queryForObject(sql, (Object[]) new Object[]{name, password}, Integer.class);
       return id;
    }
}