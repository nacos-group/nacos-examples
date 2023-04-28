package com.alibaba.nacos.grpc.internal;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.grpc.utils.NacosUtils;
import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.util.Properties;

public class NacosNameResolverProvider extends NameResolverProvider {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected  static final String NACOS = "nacos";

    private URI uri;

    public NacosNameResolverProvider(URI targetUri, Attributes params) {
        this.uri = targetUri;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 6;
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, Attributes attributes) {
        Properties properties = new Properties();
        return new NacosNameResolver(properties, targetUri, buildNamingService(properties));
    }

    @Override
    public String getDefaultScheme() {
        return NACOS;
    }

    private NamingService buildNamingService (Properties properties) {
        NamingService namingService = null;
        properties = NacosUtils.buildNacosProperties(uri, properties);
        try {
            namingService = NacosFactory.createNamingService(properties);
        } catch (NacosException e) {
            logger.error("build naming service error, msg: {}", e.getErrMsg());
        }
        return  namingService;
    }

}
