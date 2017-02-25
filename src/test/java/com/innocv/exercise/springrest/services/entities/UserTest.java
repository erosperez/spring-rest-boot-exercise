package com.innocv.exercise.springrest.services.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class UserTest {

	private static final long ID = 1L;
	private static final String BIRTHDATE = "29/10/1986";
	private static final String NAME = "Eros";	

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}


    @Test
    public void getterAndSetterUserTest() {
    	User userMock = new User();
    	userMock.setId(ID);
   	 	userMock.setName(NAME);
   	 	userMock.setBirthDate(BIRTHDATE);
    	assertEquals(Long.valueOf(ID), Long.valueOf(userMock.getId()));
    	assertEquals(NAME, userMock.getName());
    	assertEquals(BIRTHDATE, userMock.getBirthDate());
    }
}
