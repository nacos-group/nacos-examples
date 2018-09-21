package com.alibaba.nacos.example.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosProviderApplication.class, args);
	}

	@RestController
	class EchoController {
		@RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
		public String echo(@PathVariable String string) {
			return "Hello Nacos Discovery " + string;
		}
	}
}
