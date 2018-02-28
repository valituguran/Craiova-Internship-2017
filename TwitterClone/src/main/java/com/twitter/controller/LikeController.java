package com.twitter.controller;

import com.twitter.entity.Post;
import com.twitter.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class LikeController {

    @Autowired
    private LikeRepository likeRepository;


    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public Post getById(@RequestParam("id") String id) {
        Post post = this.likeRepository.findById(id);
        return post;
    }

    @RequestMapping(value = "/idUser",method = RequestMethod.GET)
    public List<Post> getByIdUser(@RequestParam("idUser") String idUser) {
        List<Post> posts = this.likeRepository.findbyIdUser(idUser);
        return posts;
    }

    @RequestMapping(value = "/idPost",method = RequestMethod.GET)
    public List<Post> getByIdPost(@RequestParam("idPost") String idPost) {
        List<Post> posts = this.likeRepository.findByIdPost(idPost);
        return posts;
    }

}
