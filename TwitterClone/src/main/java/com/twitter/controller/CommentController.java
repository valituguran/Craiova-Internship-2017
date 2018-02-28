package com.twitter.controller;

import com.twitter.entity.Post;
import com.twitter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public class CommentController {

    @Autowired
    private CommentRepository commentRepository;


    @RequestMapping(value = "/comment/id",method = RequestMethod.GET)
    public Post getById(@RequestParam("id") String id) {
        Post post = this.commentRepository.findById(id);
        return post;
    }

    @RequestMapping(value = "/comment/idUser",method = RequestMethod.GET)
    public List<Post> getByIdUser(@RequestParam("idUser") String idUser) {
        List<Post> posts = this.commentRepository.findbyIdUser(idUser);
        return posts;
    }

    @RequestMapping(value = "/comment/idPost",method = RequestMethod.GET)
    public List<Post> getByIdPost(@RequestParam("idPost") String idPost) {
        List<Post> posts = this.commentRepository.findByIdPost(idPost);
        return posts;
    }

    @RequestMapping(value = "/comment/date",method = RequestMethod.GET)
    public List<Post> getByDate(@RequestParam("date") Date date) {
        List<Post> posts = this.commentRepository.findbyDate(date);
        return posts;
    }
}
