package com.alibaba.nacos.example.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("config")
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

