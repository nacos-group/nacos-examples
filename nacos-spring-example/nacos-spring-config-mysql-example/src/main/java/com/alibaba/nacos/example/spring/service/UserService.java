package com.alibaba.nacos.example.spring.service;

import com.alibaba.nacos.example.spring.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Integer id);
}