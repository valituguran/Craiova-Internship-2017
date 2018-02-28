package com.twitter.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Comment {

    @Id
    private String id;
    private String idUser;
    private String idPostare;
    private Date date;
    private String content;


    public Comment(String id, String idUser, String idPostare, Date date, String content) {
        this.id = id;
        this.idUser = idUser;
        this.idPostare = idPostare;
        this.date = date;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPostare() {
        return idPostare;
    }

    public void setIdPostare(String idPostare) {
        this.idPostare = idPostare;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
