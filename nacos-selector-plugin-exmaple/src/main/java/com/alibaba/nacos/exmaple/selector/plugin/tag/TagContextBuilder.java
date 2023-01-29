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

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.selector.context.SelectorContextBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenglu
 * @date 2021-10-09 18:08
 */
public class TagContextBuilder implements SelectorContextBuilder<TagContext, String, List<Instance>> {

    @Override
    public TagContext build(String consumer, List<Instance> provider) {
        TagContext tagContext = new TagContext();
        List<TagContext.TagInstance> tagInstances = provider.stream()
                .map(i -> {
                    TagContext.TagInstance tagInstance = new TagContext.TagInstance();
                    tagInstance.setInstance(i);
                    if (i.getIp().equals("127.0.0.1")) {
                        tagInstance.setTag("A");
                    } else {
                        tagInstance.setTag("B");
                    }
                    return tagInstance;
                })
                .collect(Collectors.toList());
        tagContext.setTagInstances(tagInstances);
        return tagContext;
    }

    @Override
    public String getContextType() {
        return "TAG";
    }
}
