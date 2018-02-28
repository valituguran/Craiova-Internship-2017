package com.twitter.repository;

import com.twitter.entity.Post;
import com.twitter.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository  extends MongoRepository<User, String> {


    @Query("{ 'date' : ?0 }")
    List<Post> findbyDate(Date date);

    @Query("{ 'idUser' : ?0 }")
    List<Post> findByIdUser(String idUser);

    Post findById(String id);

}
