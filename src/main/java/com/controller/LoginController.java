package com.controller;

import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;
import io.javalin.http.Context;

public class LoginController {

    //constructor
    public LoginController(){

    }

    /**
     * Get's value from session representing the level of access that the user currently has
     * Adds it to the context to be sent back
     * @param context
     */
    public static void postLoginPage(Context context){
        System.out.println("Before creating int");
        Integer i = context.sessionAttribute("userType");
        System.out.println("Before creating string");
        String s = String.valueOf(i);
        System.out.println("Before setting result");
        context.result(s);
        System.out.println("After setting result");
    }

    /**
     * Takes in the user in put from the login page and turns it into into a incomplete user object
     * Then sends the information to the database to fill out the remainder of the user information
     * if it is found.
     * Sets the session Attribute "userType"
     * @param context
     */
    public static void login(Context context){

        User u = context.bodyAsClass(User.class);

        //verify the login by trying to find user with matching
        // username and password and returning other info if found
        UserService us = new UserServiceImpl();
        u = us.verifyLogin(u);

        context.sessionAttribute("userType", u.getRoleID());
        if(u.getRoleID() == 1){
            System.out.println("Verified as an employee");
            context.sessionAttribute("userinfo", u);
        }
        else if(u.getRoleID() == 2){
            System.out.println("Verified as a finance manager");
            context.sessionAttribute("userinfo", u);
        }
        else{
            System.out.println("Invalid login credentials");
        }
    }

    public static void logout(Context context){
        context.sessionAttribute("userinfo", null);
        context.sessionAttribute("userType", 0);
    }
}
