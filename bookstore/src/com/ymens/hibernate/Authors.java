package com.ymens.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "authors", schema = "bookstore")
public class Authors {
    private int id;
    private String name;
    private int age;
    private String nationality;
    private String description;
    private Long cnp;

    public Authors(String name, int age, String nationality, String description, long cnp) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.description = description;
        this.cnp = cnp;
    }

    public Authors() { }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CNP")
    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authors authors = (Authors) o;

        if (id != authors.id) return false;
        if (name != null ? !name.equals(authors.name) : authors.name != null) return false;
        if (age != authors.age) return false;
        if (nationality != null ? !nationality.equals(authors.nationality) : authors.nationality != null) return false;
        if (description != null ? !description.equals(authors.description) : authors.description != null) return false;
        if (cnp != null ? !cnp.equals(authors.cnp) : authors.cnp != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (age ^ (age >>> 32));
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cnp != null ? cnp.hashCode() : 0);
        return result;
    }
}
