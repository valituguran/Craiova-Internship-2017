package com.twitter.controller;

import com.twitter.entity.User;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/users")
    public List<User> getAll(){
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") String id){
        User user = this.userRepository.findById(id);
        return user;
    }

    @RequestMapping(value = {"/follow"},method = RequestMethod.GET)
    public User follow(@RequestParam("id_user") String id_user,@RequestParam("id_follow") String id_follow){
        System.out.println("a intrat");
        User fallowUser = getById(id_follow);
        User user = getById(id_user);
        user.addFollowingList(fallowUser.getId());
        fallowUser.addFollowerList(user.getId());
        userRepository.save(user);
        userRepository.save(fallowUser);
        return fallowUser;
        }
}