package com.ymens.spring.mapper;


import com.ymens.spring.beans.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BooksMapper implements RowMapper<Book> {

        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setIsbn(rs.getLong("isbn"));
            book.setImage(rs.getBytes("image"));
            book.setDescription(rs.getString("description"));
            book.setPrice(rs.getDouble("price"));
            book.setAuthor_id(rs.getInt("author_id"));
            return book;
        }

        public Book setBook(List<Book> list){
            Book book = new Book();
            Book b = new Book();
            for(int i=0; i<list.size(); i++){
                b = list.get(i);
                book.setId(b.getId());
                book.setName(b.getName());
                book.setIsbn(b.getIsbn());
                book.setImage(b.getImage());
                book.setDescription(b.getDescription());
                book.setPrice(b.getPrice());
                book.setAuthor_id(b.getAuthor_id());
            }
            return book;
        }
    }

