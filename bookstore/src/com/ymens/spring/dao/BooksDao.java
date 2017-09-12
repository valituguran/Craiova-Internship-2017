package com.ymens.spring.dao;

import com.ymens.spring.beans.Book;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BooksDao {
    public static List<Book> selectBooks(DataSource ds) {
        JdbcOperations jdbcTemplateObject = new JdbcTemplate(ds);
        return jdbcTemplateObject.query("select * from employee", new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rownumber) throws SQLException {
                Book b = new Book();
                b.setId(rs.getInt(1));
                b.setName(rs.getString(2));
              b.setAuthor_id(rs.getInt(3));
              b.setIsbn(rs.getInt(4));
              b.setPrice(rs.getInt(5));
              b.setDescription(rs.getString(6));
              b.setImage(rs.getBytes(7));
                return b;
            }
        });
    }
}
