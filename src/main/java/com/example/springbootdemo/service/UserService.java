package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.User;

public interface UserService {
    void register(User user);
    User login(User user);
}
