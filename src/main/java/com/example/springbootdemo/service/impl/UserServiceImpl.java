package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.entity.UserExample;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(String phone, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(phone)
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (ListUtils.isEmpty(users)) {
            return null;
        } else {
            return users.get(0);
        }

    }

}
