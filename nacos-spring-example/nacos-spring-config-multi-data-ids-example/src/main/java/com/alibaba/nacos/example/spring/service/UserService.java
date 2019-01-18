package com.alibaba.nacos.example.spring.service;

import com.alibaba.nacos.example.spring.model.User;

/**
 * @author hexu.hxy
 * @date 2019/1/7
 */
public interface UserService {

    User findById(Long id);

}