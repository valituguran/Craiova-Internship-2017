package com.twitter.entity;

import org.springframework.data.annotation.Id;

public class Like {

    @Id
    private String id;
    private String idUser;
    private String idPost;

    public Like(String id, String idUser, String idPostare) {
        this.id = id;
        this.idUser = idUser;
        this.idPost = idPostare;
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

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
}
