package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public User loginCheck(User user) {
    	return userDao.loginCheck(user);
    }
    
    @Override
    public int createUser(User user) {
    	return userDao.createUser(user);
    	
    }
    
}

