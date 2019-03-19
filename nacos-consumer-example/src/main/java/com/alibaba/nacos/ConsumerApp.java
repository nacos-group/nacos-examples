package com.alibaba.nacos;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;

/**
 * Hello world!
 */
public class ConsumerApp {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        NamingService namingService = NamingFactory.createNamingService("127.0.0.1:8848");

        namingService.subscribe("test.1", new EventListener() {
            @Override
            public void onEvent(Event event) {
                NamingEvent namingEvent = (NamingEvent) event;

                for (Instance instance : namingEvent.getInstances()) {
                    System.out.println(instance);
                }
            }
        });

        Thread.sleep(100000000L);
    }
}
