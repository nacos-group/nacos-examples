package com.liyh.controller;

import com.liyh.druid.DruidDataSourceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nacos")
@RefreshScope
public class TestController1 {
    @Autowired
    private DruidDataSourceWrapper dataSourceWrapper;

    @Value(value = "${spring.datasource.druid.master.url}")
    private String useLocalCache;

}
