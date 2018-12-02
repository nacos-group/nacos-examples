package com.alibaba.nacos.example.spring.boot.service;

import com.alibaba.nacos.example.spring.boot.dao.UserRepository;
import com.alibaba.nacos.example.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}