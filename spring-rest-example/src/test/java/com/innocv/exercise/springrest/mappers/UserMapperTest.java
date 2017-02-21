package com.innocv.exercise.springrest.mappers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.innocv.exercise.springrest.repositories.entities.UserDao;
import com.innocv.exercise.springrest.services.entities.User;

public class UserMapperTest {
 
	private static final long ID = 1L;
	private static final String BIRTHDATE = "29/10/1986";
	private static final String NAME = "Eros";
	
	@InjectMocks
	private UserMapper mapper;
	
    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    }

	
	
	@Test 
	public void mapUserDaoToUserTest(){
		UserDao userDao = getUserDao();
		User userReturned =mapper.mapUserDaoToUser(userDao);
		assertEquals(userDao.getBirthDate(),userReturned.getBirthDate());
		assertEquals(userDao.getName(),userReturned.getName());
		assertEquals(userDao.getId(),userReturned.getId());
		
	}


	@Test 
	public void mapListUsersDaoToListUsers(){
		List<UserDao> userDaoList = new ArrayList<UserDao>();
		userDaoList.add(this.getUserDao());
		userDaoList.add(this.getUserDao());
		List<User> userList = mapper.mapListUsersDaoToListUsers(userDaoList);
		assertNotNull(userList);
		assertEquals(2, userList.size());
		assertEquals(ID, userList.get(0).getId().longValue());
		assertEquals(NAME, userList.get(0).getName());
		assertEquals(BIRTHDATE, userList.get(0).getBirthDate());
		assertEquals(ID, userList.get(1).getId().longValue());
		assertEquals(NAME, userList.get(1).getName());
		assertEquals(BIRTHDATE, userList.get(1).getBirthDate());
		
		
	}
	
	private UserDao getUserDao() {
		UserDao userDao = new UserDao();
		userDao.setBirthDate(BIRTHDATE);
		userDao.setId(ID);
		userDao.setName(NAME);
		return userDao;
	}
	

   
   
}
