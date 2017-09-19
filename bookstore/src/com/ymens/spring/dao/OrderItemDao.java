package com.ymens.spring.dao;

import com.ymens.spring.beans.OrderItem;
import com.ymens.spring.interfaces.IOrderItem;
import com.ymens.spring.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class OrderItemDao implements IOrderItem {
    private static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public int setOrderItem(OrderItem orderItem) {
        String sql = "insert into `order_item` (order_id, book_id, price) values(?,?,?)";
        Object[] params=new Object[]{orderItem.getOrder_id(), orderItem.getBook_id(), orderItem.getPrice()};
        return jdbcTemplateObject.update(sql,params);
    }

    @Override
    public int getPrice(int order) {
        String sql="SELECT price FROM order_item WHERE id=?";
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{order},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int getIdOrder(int order) {
        String sql="SELECT order_id FROM order_item WHERE id=?";
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{order},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int getIdBook(int order) {
        String sql="SELECT book_id FROM order_item WHERE id=?";
        int id = 0;
        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{order},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }
    @Override
    public List<OrderItem> getOrderItems(String string) {
        String sql = "SELECT * FROM books";
        return jdbcTemplateObject.query(sql, new OrderItemMapper());
    }

    public int setOrderItem(int order_id, String name, double unitCost) {
        BooksDao b = new BooksDao();
        int id = b.getId(name);
        String sql = "insert into `order_item` (order_id, book_id, price) values(?,?,?)";
        Object[] params=new Object[]{order_id, id, unitCost};
        return jdbcTemplateObject.update(sql,params);
    }
}
