package com.innocv.exercise.springrest.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.innocv.exercise.springrest.repositories.entities.UserDao;
import com.innocv.exercise.springrest.services.entities.User;

@Component
public class UserMapper {
 
   public User mapUserDaoToUser(UserDao userDao){
	   User user = new User();
	   user.setId(userDao.getId());
	   user.setName(userDao.getName());
	   user.setBirthDate(userDao.getBirthDate());
	   return user;
	   
   }
   
   public List<User> mapListUsersDaoToListUsers(List<UserDao> userDaoList){
	   List<User> userList = null;
	   if(userDaoList != null){
		   userList = new ArrayList<User>();
		   for(UserDao userDao: userDaoList){
			   User user =this.mapUserDaoToUser(userDao);
			   userList.add(user);
		   }
   		}
	   return userList;
   }

   public UserDao mapUserToUserDao(User user) {
	   UserDao userDao = new UserDao();
	   userDao.setId(user.getId());
	   userDao.setName(user.getName());
	   userDao.setBirthDate(user.getBirthDate());
	   return userDao;
}
}
