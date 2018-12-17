package com.alibaba.nacos.example.spring.dao;

import com.alibaba.nacos.example.spring.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select(value = "select id, name from user where id = #{id}")
    User findById(@Param("id") long id);
}