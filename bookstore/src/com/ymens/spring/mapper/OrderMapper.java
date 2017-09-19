package com.ymens.spring.mapper;

import com.ymens.spring.beans.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setUserId(rs.getInt("user_id"));
        return order;
    }
}