package com.alibaba.nacos.example.spring;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.context.event.config.EventPublishingConfigService;
import com.alibaba.nacos.spring.factory.CacheableEventPublishingNacosServiceFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import static com.alibaba.nacos.spring.util.NacosBeanUtils.getNacosConfigListenerExecutorIfPresent;
import static com.alibaba.nacos.spring.util.NacosUtils.identify;

@Service("nacosServiceFactory")
public class AcmAdapterServiceFactory extends
    CacheableEventPublishingNacosServiceFactory {

    private final Map<String, ConfigService>
        configServicesCache = new LinkedHashMap<String, ConfigService>(2);

    private ConfigurableApplicationContext context;

    private ExecutorService nacosConfigListenerExecutor;

    @Override
    public ConfigService createConfigService(Properties properties) throws
        NacosException {

        Properties copy = new Properties();

        copy.putAll(properties);

        String cacheKey = identify(copy);

        ConfigService configService = configServicesCache.get(cacheKey);

        if (configService == null) {
            configService = doCreateConfigService(copy);
            configServicesCache.put(cacheKey, configService);
        }

        return configService;
    }

    private ConfigService doCreateConfigService(Properties properties) throws NacosException {
        ConfigService configService = new AcmConfigService(properties);
        return new EventPublishingConfigService(configService, properties, context, nacosConfigListenerExecutor);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws
        BeansException {
        this.context = (ConfigurableApplicationContext) applicationContext;
        this.nacosConfigListenerExecutor = getNacosConfigListenerExecutorIfPresent(applicationContext);
    }

}
