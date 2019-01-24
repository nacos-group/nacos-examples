package com.alibaba.nacos.example.spring.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;
import com.alibaba.nacos.example.spring.model.Admin;

/**
 * @author hexu.hxy
 */
public class AdminConverter implements NacosConfigConverter<Admin> {

    @Override
    public boolean canConvert(Class targetType) {
        return Admin.class.equals(targetType);
    }

    /**
     * 可自定义转化格式
     */
    @Override
    public Admin convert(String config) {
        return JSON.parseObject(config, Admin.class);
    }
}