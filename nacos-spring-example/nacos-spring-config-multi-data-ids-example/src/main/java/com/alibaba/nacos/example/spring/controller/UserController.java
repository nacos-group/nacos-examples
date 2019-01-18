package com.alibaba.nacos.example.spring.controller;

import com.alibaba.nacos.example.spring.model.User;
import com.alibaba.nacos.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hexu.hxy
 */
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    /**
     * http://localhost:8080/user?id=1
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User get(@RequestParam long id) {
        return userService.findById(id);
    }

}