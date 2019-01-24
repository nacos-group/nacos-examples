package com.alibaba.nacos.example.spring.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.example.spring.converter.AdminConverter;
import com.alibaba.nacos.example.spring.model.Admin;
import com.alibaba.nacos.example.spring.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author hexu.hxy
 * @date 2019/1/6
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private static final String ADMIN_DATA_ID = "admin.json";

    private static final String ADMIN_GROUP_ID = "spring-listener";

    private volatile Admin admin;

    @NacosConfigListener(dataId = ADMIN_DATA_ID, groupId = ADMIN_GROUP_ID)
    public void onReceived(String content) {
        LOGGER.info("onReceived(String) : {}", content);
    }

    /**
     * <p>
     * Nacos 控制台添加配置：
     * <p>
     * Data ID：admin.json
     * <p>
     * Group：spring-listener
     * <p>
     * 配置内容：
     *  {
     *      "username": "admin",
     *      "password": "123456"
     *  }
     */
    @NacosConfigListener(dataId = ADMIN_DATA_ID, groupId = ADMIN_GROUP_ID, converter = AdminConverter.class)
    public void onReceived(Admin admin) {
        LOGGER.info("onReceived(Admin) : {}", admin);
        this.admin = admin;
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }
}
