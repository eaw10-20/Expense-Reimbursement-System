package com.service;

import com.model.User;

import java.util.Map;

public interface UserService {
    public int insertUser(User u, int role);

    public Map<Integer, String> getUserMap();

    public User selectUserByID(int id);

    public User verifyLogin(User user);
}
