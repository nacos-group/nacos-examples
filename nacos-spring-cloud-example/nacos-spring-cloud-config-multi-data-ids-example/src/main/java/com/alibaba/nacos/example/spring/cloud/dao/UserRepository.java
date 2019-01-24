package com.alibaba.nacos.example.spring.cloud.dao;

import com.alibaba.nacos.example.spring.cloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}