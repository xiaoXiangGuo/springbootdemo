package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.User;

public interface UserService {
    int register(User user);
    User login(String phone,String password);
}
