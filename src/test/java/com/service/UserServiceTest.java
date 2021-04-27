package com.service;

import com.model.Employee;
import com.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService us;
    User user;

    @BeforeEach
    void setUp(){
        us = new UserServiceImpl();
        user = new Employee();
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    public void verifyLoginTest(){

        //make sure login is being activated and return is as expected

        user.setUsername("frostfuzz");
        user.setPassword("p4ssw0rd");

        user = us.verifyLogin(user);

        assertEquals(1, user.getRoleID());
        assertEquals("frostfuzz", user.getUsername());
        assertEquals("Eric", user.getFirstName());
        assertEquals("Williams", user.getLastName());
    }
}
