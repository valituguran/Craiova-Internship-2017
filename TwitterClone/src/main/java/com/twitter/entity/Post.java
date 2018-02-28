package com.twitter.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Post {

    @Id
    private String id;
    private Date date;
    private String text;
    private String content;
    private String idUser;

    public Post(String id, Date date, String text, String content, String idUser) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.content = content;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setId_user(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", content='" + content + '\'' +
                ", id_user='" + idUser + '\'' +
                '}';
    }
}
