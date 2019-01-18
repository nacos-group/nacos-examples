package com.alibaba.nacos.example.spring;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
// 在命名空间详情处可以获取到 endpoint 和 namespace；accessKey 和 secretKey 推荐使用 RAM 账户的
@EnableNacosConfig(globalProperties = @NacosProperties(
    endpoint = "xxx",
    namespace = "xxx",
    accessKey = "xxx",
    secretKey = "xxx"
))

/**
 * Document: https://help.aliyun.com/document_detail/94588.html
 * <p>
 * ACM 控制台添加配置：
 * <p>
 * Data ID：com.alibaba.nacos.example
 * <p>
 * Group：DEFAULT_GROUP
 * <p>
 * 配置内容：connectTimeoutInMills=5000
 */
@NacosPropertySource(dataId = "com.alibaba.nacos.example", autoRefreshed = true)
public class NacosConfiguration {

}