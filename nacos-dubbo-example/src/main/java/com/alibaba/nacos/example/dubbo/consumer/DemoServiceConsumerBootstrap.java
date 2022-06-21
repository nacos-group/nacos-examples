package com.alibaba.nacos.example.dubbo.consumer;


import com.alibaba.nacos.example.dubbo.service.DemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * {@link DemoService} consumer demo
 * https://nacos.io/zh-cn/docs/use-nacos-with-dubbo.html
 */
@EnableDubbo
@PropertySource(value = "classpath:/consumer-config.properties")
public class DemoServiceConsumerBootstrap {

    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            System.out.println(demoService.sayName("Nacos"));
        }
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoServiceConsumerBootstrap.class);
        context.refresh();
        context.close();
    }
}
