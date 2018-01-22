package com.ymens.spring.beans;

import org.springframework.jdbc.core.JdbcTemplate;

public class Author {

        private int id;
        private String name;
        private int age;
        private String nationality;
        private String description;
        private Long cnp;
        private JdbcTemplate jdbcTemplate;

    public Author(String name, int age, String nationality, String description, long cnp) {
            this.name = name;
            this.age = age;
            this.nationality = nationality;
            this.description = description;
            this.cnp = cnp;
        }

        public Author() { }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getCnp() {
            return cnp;
        }

        public void setCnp(Long cnp) {
            this.cnp = cnp;
        }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
