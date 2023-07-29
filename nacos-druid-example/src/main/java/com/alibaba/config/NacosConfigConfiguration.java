package com.alibaba.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.DruidDataSourceWrapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class NacosConfigConfiguration {

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    @RefreshScope

    public DruidDataSource dataSource() {
        return new DruidDataSourceWrapper();
    }
}
