package com.ymens.spring.mapper;

import com.ymens.hibernate.UserType;
import com.ymens.spring.beans.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRealName(rs.getString("real_name"));
        user.setType(UserType.getIdType(user.getUsername(), user.getPassword()));
        return user;
        }
}