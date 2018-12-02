package com.alibaba.nacos.example.spring.boot.dao;

import com.alibaba.nacos.example.spring.boot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}