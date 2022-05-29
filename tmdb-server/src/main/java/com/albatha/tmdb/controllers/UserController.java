package com.albatha.tmdb.controllers;

import java.util.List;

import com.albatha.tmdb.models.User;
import com.albatha.tmdb.services.interfaces.UserService;
import com.albatha.tmdb.utils.TmdbConstants;
import com.albatha.tmdb.utils.exceptions.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/all")
    public List<User> all(){
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        try {
            userService.createUser(user);
            return user;
        } catch (Exception e) {
            log.error(e.toString());
            throw new UserException(TmdbConstants.ERROR_USER);
        }
    }

    @CrossOrigin
    @GetMapping("/search/email/{email}")
    public User searchUserById(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

}
