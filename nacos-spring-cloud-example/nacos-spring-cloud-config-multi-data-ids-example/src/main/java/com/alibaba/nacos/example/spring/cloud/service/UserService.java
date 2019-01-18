package com.alibaba.nacos.example.spring.cloud.service;

import com.alibaba.nacos.example.spring.cloud.model.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author hexu.hxy
 * @date 2019/1/7
 */

@Service
@RefreshScope
public interface UserService {

    User findById(Long id);

}