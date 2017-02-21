package com.innocv.exercise.springrest.services.impl;

import com.innocv.exercise.springrest.mappers.UserMapper;
import com.innocv.exercise.springrest.repositories.entities.UserDao;
import com.innocv.exercise.springrest.repositories.impl.UserRepository;
import com.innocv.exercise.springrest.services.UserService;
import com.innocv.exercise.springrest.services.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceImplTest {
    private static final long USER_ID_12 = 12l;
    private static final String NAME_EROS = "Eros";
    private static final String STRING_DATE_19110102 = "1986-10-29";

    private UserService userService = Mockito.spy(UserServiceImpl.class);
    
    @Mock
    UserMapper userMapperMock;
    
    @Mock
	UserRepository userRepositoryMock;

    
    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    	Mockito.when(userService.getMapper()).thenReturn(userMapperMock);
		Mockito.when(userService.getUserRepository()).thenReturn(userRepositoryMock);
    	RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }


    @Test
    public void getUser() {
    	User userExpected =  createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102);
		
    	Mockito.when(userMapperMock.mapUserDaoToUser(Mockito.any(UserDao.class))).thenReturn(userExpected);
        
		User userReturned = userService.getUser(USER_ID_12);

        assertNotNull(userReturned);
        assertThat(userReturned.getId(), is(USER_ID_12));
    }

   

	@Test
    public void listUsers() {
		List<User> userExpectedList =  Arrays.asList(createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102),
				createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102));
		
		
		Mockito.when(userRepositoryMock.listUsers()).thenReturn(new ArrayList<UserDao>());
		Mockito.when(userMapperMock.mapListUsersDaoToListUsers(Mockito.anyListOf(UserDao.class))).thenReturn(userExpectedList);
        
        List<User> users = userService.listUsers();

        assertNotNull(users);
        assertThat(users, hasSize(2));
    }



	@Test
    public void createUser() {
    	User userExpected =  createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102);
		
    	Mockito.when(userRepositoryMock.createUser(Mockito.any(UserDao.class))).thenReturn(new UserDao());
    	Mockito.when(userMapperMock.mapUserDaoToUser(Mockito.any(UserDao.class))).thenReturn(userExpected);
        
        User user = userService.createUser(
        		createUserMock(null, NAME_EROS, STRING_DATE_19110102)
        );

        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void updateUser() {
    	User userExpected =  createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102);
    	
    	Mockito.when(userRepositoryMock.createUser(Mockito.any(UserDao.class))).thenReturn(new UserDao());
    	Mockito.when(userMapperMock.mapUserDaoToUser(Mockito.any(UserDao.class))).thenReturn(userExpected);
        
    	
        User user = userService.updateUser(
        		createUserMock(USER_ID_12, NAME_EROS, STRING_DATE_19110102)
        );

        assertNotNull(user);
        assertThat(user.getId(), is(USER_ID_12));
    }


    private User createUserMock(Long id, String name, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBirthDate(stringDate);

        return user;
    }
    
  

    
   

    
}