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

        namingService.registerInstance("test.1", "1.1.1.1", 80);
        namingService.registerInstance("test.1", "2.2.2.2", 80);
        namingService.registerInstance("test.1", "3.3.3.3", 80);
        namingService.registerInstance("test.1", "4.4.4.4", 80);
        namingService.registerInstance("test.1", "5.5.5.5", 80);
        namingService.registerInstance("test.1", "6.6.6.6", 80);

        Thread.sleep(1000000000L);
    }
}
