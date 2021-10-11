/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.exmaple.selector.plugin.tag;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.selector.Selector;
import com.alibaba.nacos.api.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenglu
 * @date 2021-10-09 18:05
 */
public class TagSelector implements Selector<List<Instance>, TagContext, String> {

    List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Selector<List<Instance>, TagContext, String> parse(String expression) throws NacosException {
        if (StringUtils.isBlank(expression)) {
            return this;
        }
        this.expression = expression;
        tags.addAll(Arrays.asList(expression.split(",")));
        return this;
    }

    @Override
    public List<Instance> select(TagContext context) {
        return context.getTagInstances().stream()
                .filter(tagInstance -> tags.contains(tagInstance.getTag()))
                .map(TagContext.TagInstance::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    public String getType() {
        return "tag";
    }

    @Override
    public String getContextType() {
        return "TAG";
    }
}
