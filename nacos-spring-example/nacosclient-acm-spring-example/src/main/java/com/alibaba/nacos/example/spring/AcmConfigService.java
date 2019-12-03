package com.alibaba.nacos.example.spring;

import java.util.Properties;
import java.util.concurrent.Executor;

import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.ConfigChangeListenerAdapter;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

public class AcmConfigService implements ConfigService {

    public AcmConfigService(Properties props) {
        com.alibaba.edas.acm.ConfigService.init(props);
    }

    @Override
    public String getConfig(String dataId, String group, long timeoutMs)
        throws NacosException {
        try {
            return com.alibaba.edas.acm.ConfigService.getConfig(dataId, group, timeoutMs);
        } catch (ConfigException e) {
            throw new NacosException(e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public void addListener(String dataId, String group, final Listener listener)
        throws NacosException {
        com.alibaba.edas.acm.ConfigService.addListener(dataId, group,
            new ConfigChangeListenerAdapter() {

            @Override
            public Executor getExecutor() {
                return listener.getExecutor();
            }

            @Override
            public void receiveConfigInfo(String s) {
                listener.receiveConfigInfo(s);
            }
        });
    }

    @Override
    public boolean publishConfig(String dataId, String group, String content)
        throws NacosException {
        try {
            return com.alibaba.edas.acm.ConfigService.publishConfig(dataId, group, content);
        } catch (ConfigException e) {
            throw new NacosException(e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public boolean removeConfig(String dataId, String group)
        throws NacosException {
        try {
            return com.alibaba.edas.acm.ConfigService.removeConfig(dataId, group);
        } catch (ConfigException e) {
            throw new NacosException(e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public void removeListener(String dataId, String group, Listener listener) {
        throw new IllegalStateException(
            "Not allow to remove listener inside acm adapter.");
    }

    @Override
    public String getServerStatus() {
        return "OK";
    }

}
