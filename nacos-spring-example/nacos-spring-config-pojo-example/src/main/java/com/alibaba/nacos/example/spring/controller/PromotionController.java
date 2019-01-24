package com.alibaba.nacos.example.spring.controller;

import com.alibaba.nacos.example.spring.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    private Promotion promotion;

    @RequestMapping(method = GET)
    @ResponseBody
    public Promotion get() {
        return promotion;
    }

}

