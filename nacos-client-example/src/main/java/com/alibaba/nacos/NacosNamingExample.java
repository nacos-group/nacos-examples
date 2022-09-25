package com.alibaba.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NacosNamingExample {
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
        NamingService naming = NacosFactory.createNamingService(properties);

        LOGGER.info("register service");
        naming.registerInstance("testService", "11.11.11.11", 8080);

        wait2Sync();

        LOGGER.info("subscribe service");
        naming.subscribe("testService", event -> {
            if (event instanceof NamingEvent) {
                LOGGER.info("serviceName:{}", ((NamingEvent) event).getServiceName());
                LOGGER.info("Instances:{}", ((NamingEvent) event).getInstances());
            }
        });

        naming.registerInstance("testService", "11.11.11.12", 8080);

        wait2Sync();
    }

    private static void wait2Sync() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
