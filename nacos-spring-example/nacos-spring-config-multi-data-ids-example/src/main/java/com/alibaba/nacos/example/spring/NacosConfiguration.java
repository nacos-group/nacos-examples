package com.alibaba.nacos.example.spring;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.context.annotation.Configuration;

@Configuration

// 在命名空间详情处可以获取到 endpoint 和 namespace；accessKey 和 secretKey 推荐使用 RAM 账户的
@EnableNacosConfig(globalProperties = @NacosProperties(
    endpoint = "xxx",
    namespace = "xxx",
    accessKey = "xxx",
    secretKey = "xxx"
))

@NacosPropertySources({

    /*
     * ACM 控制台添加配置：
     * Data ID：app.properties
     * Group：multi-data-ids
     * 配置内容：app.user.cache=false
     */
    @NacosPropertySource(dataId = "app.properties", groupId = "multi-data-ids", autoRefreshed = true),

    /*
     * ACM 控制台添加配置：
     * Data ID：datasource.properties
     * Group：multi-data-ids
     * 配置内容示例：
     *   spring.datasource.url=jdbc:mysql://localhost:3306/test?useSSL=false
     *   spring.datasource.username=root
     *   spring.datasource.password=root
     *   spring.datasource.initial-size=10
     *   spring.datasource.max-active=20
     */
    @NacosPropertySource(dataId = "datasource.properties", groupId = "multi-data-ids"),

    /* ACM 控制台添加配置：
     * Data ID：redis.properties
     * Group：multi-data-ids
     * 配置内容示例：
     *   spring.redis.host=localhost
     *   spring.redis.password=20190101
     *   spring.redis.timeout=5000
     *   spring.redis.max-idle=5
     *   spring.redis.max-active=10
     *   spring.redis.max-wait=3000
     *   spring.redis.test-on-borrow=false
     */
    @NacosPropertySource(dataId = "redis.properties", groupId = "multi-data-ids")
})
public class NacosConfiguration {

}