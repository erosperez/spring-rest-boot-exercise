package com.innocv.exercise.springrest.services;

import java.util.List;

import com.innocv.exercise.springrest.mappers.UserMapper;
import com.innocv.exercise.springrest.repositories.impl.UserRepository;
import com.innocv.exercise.springrest.services.entities.User;

public interface UserService {

    User getUser(Long id);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(User user);

    void removeUser(Long id);
    
    UserMapper getMapper();
    
    UserRepository getUserRepository();
}
