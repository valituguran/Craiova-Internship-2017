package com.ymens.spring.dao;


import com.ymens.hibernate.UserType;
import com.ymens.spring.beans.User;
import com.ymens.spring.interfaces.IUser;
import com.ymens.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUser{

    public static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public int insertUser(final User u) {
        String SQL = "insert into users (username, password, real_name, email, type) values(?,?,?,?,?)";
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
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{user.getUsername(), user.getPassword()},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public  int getId(String name, String password) {
        String sql = "select id from users where username = ? and password = ?";
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{name, password},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public User getUser(String name, String password) {
        String sql="SELECT * FROM users WHERE username=? and password=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{name, password}, new UserMapper());
    }

    @Override
    public int UpdatePassword(String pass, String user) {
        String sql = "update `users` set password=? where username=?";
        return jdbcTemplateObject.update(sql, new Object[]{pass, user});
    }

    @Override
    public int UpdateEmail(String email, String user) {
        String sql = "update `users` set email=? where username=?";
        return jdbcTemplateObject.update(sql, new Object[]{email, user});
    }

    @Override
    public int UpdateRealName(String rn, String user) {
        String sql = "update `users` set real_name=? where username=?";
        return jdbcTemplateObject.update(sql, new Object[]{rn, user});
    }




}