package com.ymens.spring.mapper;


import com.ymens.spring.beans.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {

    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem order = new OrderItem();
        order.setId(rs.getInt("id"));
        order.setPrice(rs.getDouble("price"));
        order.setBook_id(rs.getInt("book_id"));
        order.setOrder_id(rs.getInt("order_id"));
        return order;
    }
}