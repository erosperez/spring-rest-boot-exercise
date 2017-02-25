package com.innocv.exercise.springrest.services.impl;

import com.innocv.exercise.springrest.mappers.UserMapper;
import com.innocv.exercise.springrest.repositories.impl.UserRepository;
import com.innocv.exercise.springrest.services.UserService;
import com.innocv.exercise.springrest.services.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper mapper;

    @Override
    public User getUser(Long id) {
        return this.getMapper().mapUserDaoToUser(this.getUserRepository().getUser(id));
    }

	@Override
    public List<User> listUsers() {
        return this.getMapper().mapListUsersDaoToListUsers(this.getUserRepository().listUsers());
    }

    @Override
    public User createUser(User user) {
        return this.getMapper().mapUserDaoToUser(this.getUserRepository().createUser(this.getMapper().mapUserToUserDao(user)));
    }

    @Override
    public User updateUser(User user) {
        return this.getMapper().mapUserDaoToUser(this.getUserRepository().updateUser(this.getMapper().mapUserToUserDao(user)));
    }

    @Override
    public void removeUser(Long id) {
    	userRepository.removeUser(id);
    }
    
    public UserRepository getUserRepository() {
  		return userRepository;
  	}

  	public void setUserRepository(UserRepository userRepository) {
  		this.userRepository = userRepository;
  	}

  	public UserMapper getMapper() {
  		return mapper;
  	}

  	public void setMapper(UserMapper mapper) {
  		this.mapper = mapper;
  	}
}
