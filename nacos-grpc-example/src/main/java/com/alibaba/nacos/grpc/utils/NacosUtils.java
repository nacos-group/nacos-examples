package com.alibaba.nacos.grpc.utils;

import java.net.URI;
import java.util.Properties;

import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;

public class NacosUtils {

    public static Properties buildNacosProperties(URI uri, Properties properties) {
        StringBuilder serverAddrBuilder =
                new StringBuilder(uri.getHost()) // Host
                        .append(":")
                        .append(uri.getPort()); // Port

        String serverAddr = serverAddrBuilder.toString();
        properties.put(SERVER_ADDR, serverAddr);
        return properties;
    }
}
