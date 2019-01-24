package com.alibaba.nacos.example.spring.controller;

import com.alibaba.nacos.example.spring.model.Admin;
import com.alibaba.nacos.example.spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author hexu.hxy
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Admin get() {
        return adminService.getAdmin();
    }
}

