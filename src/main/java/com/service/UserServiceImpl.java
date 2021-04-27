package com.service;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.model.User;

import java.util.Map;

public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();

    @Override
    public int insertUser(User u, int role) {
        return userDao.insertUser(u, role);
    }

    @Override
    public Map<Integer, String> getUserMap() {
        return userDao.getUserMap();
    }

    @Override
    public User selectUserByID(int id) {
        return userDao.selectUserByID(id);
    }

    @Override
    public User verifyLogin(User user) {
        //get user by username
        User potentialUser = userDao.selectUserByUsername(user.getUsername());

        //compare passwords returning full dataset if they match
        if(potentialUser != null && user.getPassword().equals(potentialUser.getPassword()))
            return potentialUser;
        return user;
    }
}
