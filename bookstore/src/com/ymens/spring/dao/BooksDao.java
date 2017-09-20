package com.ymens.spring.dao;

import com.ymens.spring.beans.Book;
import com.ymens.spring.interfaces.IBook;
import com.ymens.spring.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
@Configurable
public class BooksDao implements IBook {
    public static JdbcTemplate jdbcTemplate;
   public BooksDao(){}


    public BooksDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
        String sql;
        List<Book> listBook, listB = new LinkedList<>();
        Book book;
        for(int i=0; i<list.size(); i++) {
            sql = "SELECT * FROM books where author_id="+(Integer)list.get(i);
            int j=0;
            listBook= jdbcTemplate.query(sql, new BooksMapper());
            for(; j<listBook.size(); j++) {
                 book = listBook.get(j);
                 listB.add(book);
            }
        }
        return listB;
    }
    @Override
    public List<Book> searchByName(String n){
        String sql = "SELECT * FROM books where name like ?";
        return jdbcTemplate.query(sql,new Object[]{"%"+n+"%"}, new BooksMapper());
    }

    public int getId(String name) {
        String sql = "SELECT id FROM books WHERE name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }
}

