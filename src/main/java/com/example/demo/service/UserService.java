package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    public User loginCheck(User user);
    
    public int createUser(User user);
	
}