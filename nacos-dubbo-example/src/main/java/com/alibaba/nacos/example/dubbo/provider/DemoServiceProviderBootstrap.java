package com.alibaba.nacos.example.dubbo.provider;

import com.alibaba.nacos.example.dubbo.service.DemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import java.io.IOException;

/**
 * {@link DemoService} provider demo
 * https://nacos.io/zh-cn/docs/use-nacos-with-dubbo.html
 */
@EnableDubbo(scanBasePackages = "com.alibaba.nacos.example.dubbo.service")
@PropertySource(value = "classpath:/provider-config.properties")
public class DemoServiceProviderBootstrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoServiceProviderBootstrap.class);
        context.refresh();
        System.out.println("DemoService provider is starting...");
        System.in.read();
    }
}
