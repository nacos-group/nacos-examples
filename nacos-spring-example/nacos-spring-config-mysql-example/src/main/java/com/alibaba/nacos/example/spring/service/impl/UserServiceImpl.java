package com.alibaba.nacos.example.spring.service.impl;

import com.alibaba.nacos.example.spring.dao.UserMapper;
import com.alibaba.nacos.example.spring.model.User;
import com.alibaba.nacos.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {this.userMapper = userMapper;}

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}