package com.ymens.spring.mapper;
import com.ymens.spring.beans.Author;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper  implements RowMapper<Author> {
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
      Author a = new Author();
        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        a.setAge(rs.getInt("age"));
        a.setCnp(rs.getLong("CNP"));
        a.setDescription(rs.getString("description"));
        a.setNationality(rs.getString("nationality"));
        return a;
    }
}
