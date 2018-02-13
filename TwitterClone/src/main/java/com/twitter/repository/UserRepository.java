package com.twitter.repository;

import com.twitter.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    List<User> findByThePersonsUsername(String username);

    @Query("{ 'password' : ?0 }")
    List<User> findByThePersonsPassword(String password);

    @Query("{ $and: [ { 'username' : ?0} ,{ 'password' : ?1 } ] }")
    User loginQuery(String username,String password);

    User findById(String id);

}