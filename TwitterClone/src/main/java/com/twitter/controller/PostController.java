package com.twitter.controller;


import com.twitter.entity.Post;
import com.twitter.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9999", maxAge = 3600)
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @RequestMapping(value = "/post/id",method = RequestMethod.GET)
    public Post getById(@RequestParam("id") String id) {
        Post post = this.postRepository.findById(id);
        return post;
    }

    @RequestMapping(value = "/post/idUser",method = RequestMethod.GET)
    public List<Post> getByIdUser(@RequestParam("id_user") String idUser) {
        List<Post> post = this.postRepository.findByIdUser(idUser);
        return post;
    }

    @RequestMapping(value = "/post/date",method = RequestMethod.GET)
    public List<Post> getByDate(@RequestParam("date") Date date) {
        List<Post> posts = this.postRepository.findbyDate(date);
        return posts;
    }
}
