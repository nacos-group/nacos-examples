package com.alibaba.nacos.example.spring.boot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Document: https://help.aliyun.com/document_detail/94592.html
 * <p>
 * ACM 控制台添加配置：
 * <p>
 * Data ID：com.alibaba.nacos.example.properties
 * <p>
 * Group：DEFAULT_GROUP
 * <p>
 * 配置内容：connectTimeoutInMills=5000
 */
@SpringBootApplication
@NacosPropertySource(dataId = "com.alibaba.nacos.example.properties", autoRefreshed = true)
public class NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}