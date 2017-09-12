package com.ymens.spring.beans;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class User {
      private int id;
        private String username;
         private String password;
        private String realName;
        private String email;
        private int type;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    public User(){}

        public User(String username, String password, String realname, String email, int type) {
            this.username = username;
            this.password = password;
            this.realName = realname;
            this.email = email;
            this.type = type;
        }

    public  DataSource getDataSource() {
        return this.dataSource;
    }
    public void setDataSource(DataSource dataSource) {
         this.dataSource = dataSource;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}