package com.innocv.exercise.springrest.repositories.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.stereotype.Component;

import com.innocv.exercise.springrest.Application;
import com.innocv.exercise.springrest.repositories.entities.UserDao;

import static org.junit.Assert.*;

import java.util.List;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes= Application.class)

public class UserRepositoryTest {

	private static final String BIRTHDATE = "2010-01-01";
	private static final String NAME = "John";
	
	@Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        List<UserDao> usersDao = userRepository.listUsers();
        assertNotNull(usersDao);
        assertTrue(!usersDao.isEmpty());
    }
 
    @Test
    public void findUserByIdTest() {
        UserDao userDao = userRepository.getUser(1L);
        assertNotNull(userDao);
    }
 
    @Test
    public void insertUserTest() {
        UserDao userDao = new UserDao(0L, NAME, BIRTHDATE);
        UserDao savedUser = userRepository.createUser(userDao);
        UserDao newUser = userRepository.getUser(savedUser.getId());
        assertNotNull(newUser);
        assertEquals(NAME, newUser.getName());
        assertEquals(BIRTHDATE, newUser.getBirthDate());
    }

}
