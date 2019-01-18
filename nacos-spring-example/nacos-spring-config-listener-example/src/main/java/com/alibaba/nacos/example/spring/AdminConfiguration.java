package com.alibaba.nacos.example.spring;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
// 在命名空间详情处可以获取到 endpoint 和 namespace；accessKey 和 secretKey 推荐使用 RAM 账户的
@EnableNacosConfig(globalProperties = @NacosProperties(
    endpoint = "xxx",
    namespace = "xxx",
    accessKey = "xxx",
    secretKey = "xxx"
))
public class AdminConfiguration {

}