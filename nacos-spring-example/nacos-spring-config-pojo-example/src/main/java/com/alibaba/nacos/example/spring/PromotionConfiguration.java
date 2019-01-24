package com.alibaba.nacos.example.spring;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.example.spring.model.Promotion;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
public class PromotionConfiguration {

    @Bean
    public Promotion promotion() {
        return new Promotion();
    }

}