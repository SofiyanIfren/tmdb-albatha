package com.albatha.tmdb.services.implementations;

import java.util.List;

import com.albatha.tmdb.dao.UserRepository;
import com.albatha.tmdb.models.User;
import com.albatha.tmdb.services.interfaces.UserService;
import com.albatha.tmdb.utils.TmdbConstants;
import com.albatha.tmdb.utils.exceptions.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User getUserByEmail(String eMail) {
        if (userRepository.findUserByEmail(eMail) == null){
            throw new UserException(TmdbConstants.ERROR_USER_DOESNT_EXISTS);
        }
        return userRepository.findUserByEmail(eMail);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null){
            throw new UserException(TmdbConstants.ERROR_USER_ALREADY_EXISTS);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(long userId){ //!\ only for administration purposes
        if (getUserById(userId) == null){
            throw new UserException(TmdbConstants.ERROR_USER_DOESNT_EXISTS);
        }
        userRepository.deleteById(userId);
    }
}
