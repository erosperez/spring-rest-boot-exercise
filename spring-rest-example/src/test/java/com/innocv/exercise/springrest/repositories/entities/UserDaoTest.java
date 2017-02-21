package com.innocv.exercise.springrest.repositories.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserDaoTest {

	private static final long ID = 1L;
	private static final String BIRTHDATE = "29/10/1986";
	private static final String NAME = "Eros";	


    @Test
    public void getterAndSetterUserDaoTest() {
    	UserDao userDaoMock = new UserDao();
    	userDaoMock.setId(ID);
    	userDaoMock.setName(NAME);
    	userDaoMock.setBirthDate(BIRTHDATE);
    	assertEquals(Long.valueOf(ID), Long.valueOf(userDaoMock.getId()));
    	assertEquals(NAME, userDaoMock.getName());
    	assertEquals(BIRTHDATE, userDaoMock.getBirthDate());
    }
}
