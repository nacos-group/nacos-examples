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

package com.alibaba.nacos.exmaple.selector.plugin;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.selector.AbstractCmdbSelector;
import com.alibaba.nacos.api.selector.context.CmdbContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example use of selector.
 *
 * @author chenglu
 * @date 2021-08-09 19:33
 */
public class ExampleCmdbSelector extends AbstractCmdbSelector<Instance> {

    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    protected List<Instance> doSelect(CmdbContext<Instance> context) {
        return context.getProviders().stream()
                .filter(ci -> tags.contains(ci.getEntity().getName()))
                .map(CmdbContext.CmdbInstance::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    protected void doParse(String expression) throws NacosException {
        tags.addAll(Arrays.asList(expression.split(",")));
    }

    @Override
    public String getType() {
        return "example";
    }
}
