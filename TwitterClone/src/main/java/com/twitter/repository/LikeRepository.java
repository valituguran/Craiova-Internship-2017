package com.twitter.repository;

import com.twitter.entity.Post;
import com.twitter.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository  extends MongoRepository<User, String> {

    @Query("{ 'idUser' : ?0 }")
    List<Post> findbyIdUser(String idUser);

    @Query("{ 'idPost' : ?0 }")
    List<Post> findByIdPost(String idPost);

    Post findById(String id);

}