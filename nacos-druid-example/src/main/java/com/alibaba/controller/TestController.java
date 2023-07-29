package com.alibaba.controller;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.DruidDataSourceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("nacos")
@RefreshScope
public class TestController {

    @Autowired
    private DruidDataSourceWrapper dataSourceWrapper;

    @Value(value = "${spring.datasource.druid.master.url}")
    private String useLocalCache;

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        System.out.println("url: " + useLocalCache);
        return useLocalCache;
    }

    @GetMapping("/query")
    public String testDruid() throws SQLException {
        DruidPooledConnection connection = dataSourceWrapper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from sys_user where user_id = 1;");
        String result = "没有此用户！！！";
        while (resultSet.next()) {
            result =  resultSet.getString("user_name") + " : " + resultSet.getString("nick_name");
            System.out.println(result);
            return result;
        }
        return result;
    }
}
