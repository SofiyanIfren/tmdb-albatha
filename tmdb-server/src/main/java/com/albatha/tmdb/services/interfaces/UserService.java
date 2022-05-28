package com.albatha.tmdb.services.interfaces;

import java.util.List;

import com.albatha.tmdb.models.User;

public interface UserService {

    User getUserById(long userId);
    User getUserByEmail(String string);
    User createUser(User user);
    List<User> getAllUsers();
    void deleteUserById(long userId);

}
