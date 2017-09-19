package com.ymens.spring.dao;


import com.ymens.spring.beans.Order;
import com.ymens.spring.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class OrderDao {
    private static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public int setOrder(double orderTotal, int user_id) {
        String sql = "insert into `order` (total_price, user_id) values(?,?,?)";
        Object[] params=new Object[]{orderTotal, user_id};
        return jdbcTemplateObject.update(sql,params);
    }
    public int setOrder(Order order) {
        String sql = "insert into `order` (total_price, user_id) values(?,?,?)";
        Object[] params=new Object[]{order.getTotalPrice(), order.getUserId()};
        return jdbcTemplateObject.update(sql,params);
    }
     public int getId(double total_price){
         String sql="SELECT id FROM order WHERE total_price=?";
         int id = 0;
         try {
             id = jdbcTemplateObject.queryForObject(sql, new Object[]{total_price},Integer.class);
         }catch(EmptyResultDataAccessException e){
             id = 0;
             e.printStackTrace();
         }
         return id;
     }


    public List<Order> getOrders(String username, String password) {
         UserDao user = new UserDao();
         int id = user.getId(username, password);
         String sql = "select * from orders where id=?";
         return jdbcTemplateObject.query(sql, new Object[]{id}, new OrderMapper());
    }

    public double getTotalPrice(String object_id) {
       int id = 0;
        try {
            id = Integer.parseInt(object_id);
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        String sql="SELECT total_price FROM order WHERE id=?";
        double total_price = 0;
        try {
            total_price = jdbcTemplateObject.queryForObject(sql, new Object[]{id},Integer.class);
        }catch(EmptyResultDataAccessException e){
            total_price = 0;
            e.printStackTrace();
        }
        return total_price;
    }

}
