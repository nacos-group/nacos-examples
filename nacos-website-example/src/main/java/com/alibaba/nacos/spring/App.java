package com.alibaba.nacos.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Demo for dynamic Page by nacos
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfiguration.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(App.class);
        SpringApplication.run(App.class, args);
    }
}
