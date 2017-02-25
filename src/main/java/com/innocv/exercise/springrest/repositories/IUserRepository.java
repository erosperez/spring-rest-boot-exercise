package com.innocv.exercise.springrest.repositories;

import java.util.List;

import com.innocv.exercise.springrest.repositories.entities.UserDao;


public interface IUserRepository {

    UserDao getUser(Long id);

    List<UserDao> listUsers();

    UserDao createUser(UserDao user);

    UserDao updateUser(UserDao user);

    void removeUser(Long id);
}
