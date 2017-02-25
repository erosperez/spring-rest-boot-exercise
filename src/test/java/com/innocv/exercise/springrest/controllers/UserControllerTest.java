package com.innocv.exercise.springrest.controllers;

import com.innocv.exercise.springrest.services.UserService;
import com.innocv.exercise.springrest.services.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static final long USER_ID_12 = 12l;
    private static final long USER_ID_140 = 140l;
    private static final long USER_ID_453321 = 45332l;
    private static final String NAME_EROS = "Eros";
    private static final String NAME_JOHN = "John";
    private static final String NAME_FRODO = "Frodo";
    private static final String STRING_DATE_19110102 = "1911-01-02";
    private static final String STRING_DATE_19230716 = "1923-07-16";
    private static final String STRING_DATE_19511124 = "1951-11-24";

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    
    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    	 RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }


    @Test
    public void getAllUsersWithSeveralUsersTest() {
        when(userService.listUsers()).thenReturn(Arrays.asList(
                createUser(USER_ID_12, NAME_EROS, STRING_DATE_19110102),
                createUser(USER_ID_140, NAME_JOHN, STRING_DATE_19230716),
                createUser(USER_ID_453321, NAME_FRODO, STRING_DATE_19511124)
        ));

        List<User> users = userController.getAllUsers();

        verify(userService).listUsers();
        assertNotNull(users);
        assertThat(users, hasSize(3));
    }

    @Test
    public void getAllUsersWithoutUsersTest() {
        when(userService.listUsers()).thenReturn(new ArrayList<User>(0));

        List<User> users = userController.getAllUsers();

        verify(userService).listUsers();
        assertNotNull(users);
        assertThat(users, hasSize(0));
    }

    @Test
    public void getUserExistUserTest() {
        when(userService.getUser(USER_ID_12)).thenReturn(
                createUser(USER_ID_12, NAME_EROS, STRING_DATE_19110102));

        ResponseEntity<User> response = userController.getUser(USER_ID_12);

        verify(userService).getUser(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), isA(User.class));
        assertThat(response.getBody().getId(), is(USER_ID_12));
    }

    @Test
    public void getUserNotExistUserTest() {
        when(userService.getUser(USER_ID_12)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(USER_ID_12);


        verify(userService).getUser(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertNull(response.getBody());
    }

    @Test
    public void createUserOKTest() {
        User user = createUser(null, NAME_EROS, STRING_DATE_19110102);
        User usercreateUserd = createUser(USER_ID_12, NAME_EROS, STRING_DATE_19110102);
        when(userService.createUser(any(User.class))).thenReturn(usercreateUserd);

        ResponseEntity<User> response = userController.createUser(user);

        verify(userService).createUser(user);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertNull(response.getBody());
        assertThat(response.getHeaders().getLocation().toString(), endsWith("/" + USER_ID_12));
    }

    @Test
    public void updateUserExistsTest() {
        User userOld = createUser(USER_ID_12, NAME_EROS, STRING_DATE_19110102);
        User user = createUser(USER_ID_12, null, null);
        when(userService.getUser(USER_ID_12)).thenReturn(userOld);
        when(userService.updateUser(any(User.class))).then(returnsFirstArg());

        userController.updateUser(user);

        verify(userService).getUser(USER_ID_12);
        verify(userService).updateUser(userOld.setName(NAME_JOHN));
    }

    @Test
    public void updateUserNotExistsTest() {
        when(userService.getUser(USER_ID_12)).thenReturn(null);
        User user = createUser(USER_ID_12, null, null);

        userController.updateUser(user);

        verify(userService).getUser(USER_ID_12);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    public void removeUserUserTest() {
        doNothing().when(userService).removeUser(anyLong());

        userController.removeUser(USER_ID_12);

        verify(userService).removeUser(USER_ID_12);
    }

    private User createUser(Long id, String name, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBirthDate(stringDate);
       
        return user;
    }
}