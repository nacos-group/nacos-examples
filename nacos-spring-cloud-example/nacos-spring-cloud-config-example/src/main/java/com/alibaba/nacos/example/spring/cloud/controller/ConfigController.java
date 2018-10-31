package com.alibaba.nacos.example.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${connectTimeoutInMills:5000}")
    private int connectTimeoutInMills;

    public void setConnectTimeoutInMills(int connectTimeoutInMills) {
        this.connectTimeoutInMills = connectTimeoutInMills;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public int get() {
        return connectTimeoutInMills;
    }
}