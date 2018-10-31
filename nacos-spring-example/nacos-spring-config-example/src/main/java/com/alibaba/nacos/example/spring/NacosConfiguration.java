package com.alibaba.nacos.example.spring;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
// 在命名空间详情处可以获取到 endpoint 和 namespace；accessKey 和 secretKey 推荐使用 RAM 账户的
@EnableNacosConfig(globalProperties = @NacosProperties(endpoint = "${endpoint}",
    namespace = "${namespace}", accessKey = "${accessKey}",
    secretKey = "${secretKey}", contextPath = "diamond-server/nacos"))
@NacosPropertySource(dataId = "com.alibaba.nacos.example", autoRefreshed = true)
public class NacosConfiguration {

}