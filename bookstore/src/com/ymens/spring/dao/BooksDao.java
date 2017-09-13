package com.ymens.spring.dao;

import com.ymens.spring.AppConfig;
import com.ymens.spring.beans.Book;
import com.ymens.spring.interfaces.IBook;
import com.ymens.spring.mapper.BooksMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BooksDao implements IBook {
    static DataSource dataSource = AppConfig.dataSource();
    static JdbcOperations jdbcTemplateObject = new JdbcTemplate(dataSource);

        @Override
        public List<Book> selectBooks(){
            String sql="SELECT * FROM books";
            return jdbcTemplateObject.query(sql, new BooksMapper());
        }



    @Override
    public Book getBook(int id) {
        String sql="SELECT * FROM books WHERE id=?";
            return jdbcTemplateObject.queryForObject(sql, new Object[]{id},new BooksMapper());
    }


    @Override
    public int addBook(Book book) {
      String sql = "insert into `books` (name, author_id, isbn, price, description, image) values(?,?,?,?,?, ?)";
        Object[] params=new Object[]{book.getName(), book.getAuthor_id(), book.getIsbn(), book.getPrice(), book.getDescription(), book.getImage()};
        return jdbcTemplateObject.update(sql,params);
    }

    @Override
    public int getIdAuthor(long isbn) {
        String sql="SELECT author_id FROM books WHERE isbn=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{isbn},Integer.class);
    }
}

