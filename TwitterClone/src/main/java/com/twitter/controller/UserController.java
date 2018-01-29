package com.twitter.controller;

import com.mongodb.util.JSON;
import com.twitter.entity.User;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody User user) {
        userRepository.save(user);
        return user.showDetails();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User user) {

        if (userRepository.loginQuery(user.getUsername(), user.getPassword()).size() != 0) {
            for (User u : userRepository.loginQuery(user.getUsername(), user.getPassword())) {
                return "Login Succesfull!" + u.getUsername() + "  " + u.getPassword();
            }
        } else {
            return "Login Failed!";
        }
        return "ala";
    }
}