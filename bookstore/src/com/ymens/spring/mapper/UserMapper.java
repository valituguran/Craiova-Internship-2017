package com.ymens.spring.mapper;


import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserTypeDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    UserTypeDao userTypeDao = new UserTypeDao();
public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRealName(rs.getString("real_name"));
        user.setType(userTypeDao.getIdType(user.getUsername(), user.getPassword()));
        return user;
        }
}