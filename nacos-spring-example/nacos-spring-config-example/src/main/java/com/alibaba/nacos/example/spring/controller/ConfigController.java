package com.alibaba.nacos.example.spring.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosInjected
    private ConfigService configService;

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    /**
     * curl -X POST 'http://localhost:8080/config?dataId=example&content=useLocalCache=true'
     */
    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity<String> publish(@RequestParam String dataId,
                                                @RequestParam(defaultValue = "DEFAULT_GROUP") String group,
                                                @RequestParam String content) {
        boolean result = false;
        try {
            result = configService.publishConfig(dataId, group, content);
        } catch (NacosException e) {
            return new ResponseEntity<String>("Publish Fail:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result) {
            return new ResponseEntity<String>("Publish Success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Publish Fail, Retry", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

