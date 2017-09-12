package com.ymens.spring.dao;


import com.ymens.spring.beans.Author;
import com.ymens.spring.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


public class AuthorsDao extends JdbcTemplate {

    private  DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public DataSource getDataSource() {
        return dataSource;
    }
    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  Author getAuthor(int id) {
        String sql = "select  from authors where id = ?";
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        Author a = (Author) jdbcTemplate.queryForObject(sql, (Object[]) new Object[]{id}, Author.class);
        return a;
    }
    public int getIdAuthor(Book b) {
        String sql = "select id from books where author_id = ?";
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        int id = (int) jdbcTemplate.queryForObject(sql, (Object[]) new Object[]{b.getAuthor_id()}, Integer.class);
        return id;
    }
}
