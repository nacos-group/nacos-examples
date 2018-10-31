/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.example.sentinel;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * Nacos config sender for demo.
 *
 * @author Eric Zhao
 */
public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String remoteAddress = "localhost";
//        final String remoteAddress = "47.74.227.13";
        final String groupId = "DEFAULT_GROUP";
        final String dataId = "com.alibaba.nacos.demo.flow.rule";
        final String rule = "[\n"
            + "  {\n"
            + "    \"resource\": \"TestResource\",\n"
            + "    \"count\": 5.0\n"
            + "  }\n"
            + "]";
        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}
