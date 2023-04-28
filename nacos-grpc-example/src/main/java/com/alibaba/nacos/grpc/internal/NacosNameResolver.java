package com.alibaba.nacos.grpc.internal;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class NacosNameResolver extends NameResolver {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String serviceName;
    private final Properties properties;
    private final NamingService namingService;

    public NacosNameResolver(Properties properties, URI targetUri, NamingService namingService) {
        this.properties = properties;
        this.serviceName = targetUri.getAuthority();
        this.namingService = namingService;
    }

    @Override
    public String getServiceAuthority() {
        return serviceName;
    }

    @Override
    public void start(Listener listener) {
        update(listener);
    }

    @Override
    public void shutdown() {

    }

    private void update(Listener listener) {

        try {
            List<Instance> instances = namingService.getAllInstances(serviceName);

            List<EquivalentAddressGroup> equivalentAddressGroups = instances.stream().map(instance -> {
                int port = instance.getPort();
                return new EquivalentAddressGroup(new InetSocketAddress(instance.getIp(), port));
            }).collect(Collectors.toList());

            listener.onAddresses(equivalentAddressGroups, Attributes.EMPTY);
        } catch (NacosException e) {
            logger.error(e.getErrMsg());
        }

    }
}