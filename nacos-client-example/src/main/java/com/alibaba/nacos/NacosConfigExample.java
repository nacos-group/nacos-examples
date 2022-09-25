package com.alibaba.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NacosConfigExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(NacosConfigExample.class);

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        // 指定 Nacos 地址
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        // 默认命名空间是空，可以不填写
//        properties.put(PropertyKeyConst.NAMESPACE, "${namespace}");
        // 如果在云上开启鉴权可以传入应用身份
        // properties.put("ramRoleName", "$ramRoleName");
//        properties.put(PropertyKeyConst.ACCESS_KEY, "${accessKey}");
//        properties.put(PropertyKeyConst.SECRET_KEY, "${secretKey}");

        ConfigService configService = NacosFactory.createConfigService(properties);

        // 指定配置的 DataID 和 Group
        String dataId = "testDataId";
        String group = "testGroup";
        String content = "connectTimeoutInMills=5000";

        // 发布配置
        boolean publishConfig = configService.publishConfig(dataId, group, content);
        LOGGER.info("publishConfig: {}", publishConfig);
        wait2Sync();
        // 查询配置
        String config = configService.getConfig(dataId, group, 5000);
        LOGGER.info("getConfig: {}", config);
        // 监听配置
        configService.addListener(dataId, group, new PropertiesListener() {
            @Override
            public void innerReceive(Properties properties) {
                // 由于properties对象结构，默认反序列化
                LOGGER.info("innerReceive: {}", properties);
            }
        });

        configService.addListener(dataId, group, new AbstractListener() {
            @Override
            public void receiveConfigInfo(String s) {
                // 如果是json/yaml/pojo对象，可以根据需要做反序列化
                LOGGER.info("innerReceive: {}", s);
            }
        });

        // 更新配置
        boolean updateConfig = configService.publishConfig(dataId, group, "connectTimeoutInMills=3000");
        LOGGER.info("updateConfig: {}", updateConfig);
        wait2Sync();
        // 删除配置
        boolean removeConfig = configService.removeConfig(dataId, group);
        LOGGER.info("removeConfig: {}", removeConfig);
        wait2Sync();
        config = configService.getConfig(dataId, group, 5000);
        LOGGER.info("getConfig: {}", config);
    }

    private static void wait2Sync() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}