package com.example.demo.dao;

import com.example.demo.entity.User;

public interface UserDao {
    public User loginCheck(User user);
    
    public int createUser(User user);
}

