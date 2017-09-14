package com.ymens.spring.dao;


import com.ymens.spring.beans.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class OrderDao {
    private static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


    public int setOrder(Order order) {
        String sql = "insert into `order` (total_price, user_id) values(?,?,?)";
        Object[] params=new Object[]{order.getTotalPrice(), order.getUserId()};
        return jdbcTemplateObject.update(sql,params);
    }
     public int getId(double total_price){
         String sql="SELECT price FROM order WHERE total_price=?";
         int id = 0;
         try {
             id = jdbcTemplateObject.queryForObject(sql, new Object[]{total_price},Integer.class);
         }catch(EmptyResultDataAccessException e){
             id = 0;
             e.printStackTrace();
         }
         return id;
     }
}
