package com.dao;

import com.model.User;

import java.util.Map;

public interface UserDao {
    //create
    public int insertUser(User u, int role);
    //read
    public Map<Integer, String> getUserMap();
    public User selectUserByID(int id);
    public User selectUserByUsername(String username);
    //update
    //delete

    //h2 database functionality
//    public void h2InitDao();
//    public void h2DestroyDao();
}
