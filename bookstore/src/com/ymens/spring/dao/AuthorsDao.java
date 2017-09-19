package com.ymens.spring.dao;


import com.ymens.spring.beans.Author;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AuthorsDao implements IAuthor {
    private static JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Author> selectAuthors() {
        String sql="SELECT * FROM authors";
        return jdbcTemplateObject.query(sql, new AuthorMapper());
    }
    @Override
    public  Author getAuthor(long cnp) {
        String sql = "select * from authors where CNP = ?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{cnp}, new AuthorMapper());

    }
    @Override
    public  Author getAuthor(int id) {
        String sql = "select * from authors where id = ?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{id},  new AuthorMapper());

    }
    @Override
    public int addAuthor(Author a) {
        String sql = "insert into `authors` (name, age,nationality, description, CNP) values(?,?,?,?,?)";
        Object[] params=new Object[]{a.getName(), a.getAge(), a.getNationality(), a.getDescription(), a.getCnp()};
        return jdbcTemplateObject.update(sql,params);
    }

    @Override
    public int getId(int cnp) {
        String sql="SELECT `id` FROM authors WHERE CNP=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{cnp},Integer.class);
    }
    @Override
    public String getName(int id) {
        String sql="SELECT `name` FROM authors WHERE `id`=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{id},String.class);
    }
    @Override
    public int getIdAuthor(long cnp) {
        String sql="SELECT id FROM authors WHERE CNP=?";
        int id = 0;

        try {
            id = jdbcTemplateObject.queryForObject(sql, new Object[]{cnp},Integer.class);
        }catch(EmptyResultDataAccessException e){
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

}
