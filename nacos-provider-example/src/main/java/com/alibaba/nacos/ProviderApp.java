package com.alibaba.nacos;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

/**
 * Hello world!
 */
public class ProviderApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        NamingService namingService = NamingFactory.createNamingService("127.0.0.1:8848");

        String serviceName = "test.1";
        namingService.registerInstance(serviceName, "1.1.1.1", 80);
        namingService.registerInstance(serviceName, "2.2.2.2", 80);
        namingService.registerInstance(serviceName, "3.3.3.3", 80);
        namingService.registerInstance(serviceName, "4.4.4.4", 80);
        namingService.registerInstance(serviceName, "5.5.5.5", 80);
        namingService.registerInstance(serviceName, "6.6.6.6", 80);

        Thread.sleep(1000000000L);
    }
}
