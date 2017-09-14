package com.ymens.spring.dao;

import com.ymens.spring.beans.Book;
import com.ymens.spring.interfaces.IBook;
import com.ymens.spring.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BooksDao implements IBook {
    public static JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Book> selectBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BooksMapper());
    }

    @Override
    public Book getBook(int id) {
        String sql = "SELECT * FROM books WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BooksMapper());
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into `books` (name, author_id, isbn, price, description, image) values(?,?,?,?,?, ?)";
        Object[] params = new Object[]{book.getName(), book.getAuthor_id(), book.getIsbn(), book.getPrice(), book.getDescription(), book.getImage()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int getIdAuthor(long isbn) {
        String sql = "SELECT author_id FROM books WHERE isbn=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{isbn}, Integer.class);
    }

    @Override
    public Book getBook(String name) {
        String sql = "SELECT * FROM books WHERE name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BooksMapper());
    }

    @Override
    public int getIdAuthor(String name) {
        String sql = "SELECT author_id FROM books WHERE name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }

    @Override
    public List<Integer> searchAuthors(String n) {
        String sql = "SELECT id FROM authors where name like ? ";
        List<Integer> data = jdbcTemplate.queryForList(sql, new Object[]{"%" + n + "%"}, Integer.class);
        return data;
    }

    @Override
    public List<Book> searchbyAuthor(List list) {
        String sql ;
        List<Book> listBook, listB = new LinkedList<>();
        Book book;
        for(int i=0; i<list.size(); i++) {
            sql = "SELECT * FROM books where author_id="+(Integer)list.get(i);
            listBook= jdbcTemplate.query(sql, new BooksMapper());
            for(int j=0; j<listBook.size(); j++) {
                 book = listBook.get(i);
                 listB.add(book);
            }
        }
        return listB;
    }
}

