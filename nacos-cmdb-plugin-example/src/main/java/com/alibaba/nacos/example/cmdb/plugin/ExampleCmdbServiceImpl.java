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
package com.alibaba.nacos.example.cmdb.plugin;

import com.alibaba.nacos.api.cmdb.CmdbService;
import com.alibaba.nacos.api.cmdb.pojo.Entity;
import com.alibaba.nacos.api.cmdb.pojo.EntityEvent;
import com.alibaba.nacos.api.cmdb.pojo.EntityEventType;
import com.alibaba.nacos.api.cmdb.pojo.Label;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:zpf.073@gmail.com">nkorange</a>
 */
public class ExampleCmdbServiceImpl implements CmdbService {

    private int index = 1;

    private Map<String, Map<String, Entity>> entityMap = new ConcurrentHashMap<>();

    private Map<String, Label> labelMap = new ConcurrentHashMap<>();

    public ExampleCmdbServiceImpl() {

        Label label = new Label();
        label.setName("labelA");

        Set<String> values = new HashSet<>();
        values.add("a1");
        values.add("a2");
        values.add("a3");
        label.setValues(values);

        labelMap.put(label.getName(), label);

        label = new Label();
        label.setName("labelB");

        values = new HashSet<>();
        values.add("b1");
        values.add("b2");
        values.add("b3");
        label.setValues(values);

        labelMap.put(label.getName(), label);

        label = new Label();
        label.setName("labelC");

        values = new HashSet<>();
        values.add("c1");
        values.add("c2");
        values.add("c3");
        label.setValues(values);

        labelMap.put(label.getName(), label);

        entityMap.put("ip", new HashMap<String, Entity>());

        Entity entity = new Entity();
        entity.setName("1.1.1.1");
        entity.setType("ip");
        Map<String, String> labels = new HashMap<>();

        labels.put("labelA", "a1");
        labels.put("labelB", "b1");

        entity.setLabels(labels);

        entityMap.get("ip").put("1.1.1.1", entity);

        entity = new Entity();
        entity.setName("2.2.2.2");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a2");
        labels.put("labelB", "b2");

        entity.setLabels(labels);

        entityMap.get("ip").put("2.2.2.2", entity);

        entity = new Entity();
        entity.setName("3.3.3.3");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a3");
        labels.put("labelB", "b3");

        entity.setLabels(labels);

        entityMap.get("ip").put("3.3.3.3", entity);

        entity = new Entity();
        entity.setName("4.4.4.4");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a1");
        labels.put("labelB", "b1");

        entity.setLabels(labels);

        entityMap.get("ip").put("4.4.4.4", entity);

        entity = new Entity();
        entity.setName("5.5.5.5");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a1");
        labels.put("labelB", "b1");

        entity.setLabels(labels);

        entityMap.get("ip").put("5.5.5.5", entity);

        entity = new Entity();
        entity.setName("6.6.6.6");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a2");
        labels.put("labelB", "b2");

        entity.setLabels(labels);

        entityMap.get("ip").put("6.6.6.6", entity);

        entity = new Entity();
        entity.setName("7.7.7.7");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a2");
        labels.put("labelB", "b2");

        entity.setLabels(labels);

        entityMap.get("ip").put("7.7.7.7", entity);

        entity = new Entity();
        entity.setName("8.8.8.8");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a3");
        labels.put("labelB", "b3");

        entity.setLabels(labels);

        entityMap.get("ip").put("8.8.8.8", entity);

        entity = new Entity();
        entity.setName("9.9.9.9");
        entity.setType("ip");
        labels = new HashMap<>();

        labels.put("labelA", "a3");
        labels.put("labelB", "b3");

        entity.setLabels(labels);

        entityMap.get("ip").put("9.9.9.9", entity);
    }

    @Override
    public Set<String> getLabelNames() {
        Set<String> labelNames = new HashSet<>();
        labelNames.add("labelA");
        labelNames.add("labelB");
        labelNames.add("labelC");
        return labelNames;
    }

    @Override
    public Set<String> getEntityTypes() {
        Set<String> types = new HashSet<>();
        types.add("ip");
        return types;
    }

    @Override
    public Label getLabel(String labelName) {
        return labelMap.get(labelName);
    }

    @Override
    public String getLabelValue(String entityValue, String entityType, String labelName) {
        return entityMap.get(entityType).get(entityValue).getLabels().get(labelName);
    }

    @Override
    public Map<String, String> getLabelValues(String entityValue, String entityType) {
        return entityMap.get(entityType).get(entityValue).getLabels();
    }

    @Override
    public Map<String, Map<String, Entity>> dumpAllEntities() {
        return entityMap;
    }

    @Override
    public List<EntityEvent> getLabelEvents(long timestamp) {

        Entity entity = new Entity();
        entity.setName("1.1.1.1");
        entity.setType("ip");
        Map<String, String> labels = new HashMap<>();

        labels.put("labelA", "a" + ((index % 3) + 1));
        labels.put("labelB", "b" + ((index++ % 3) + 1));

        entity.setLabels(labels);

        entityMap.get("ip").put("1.1.1.1", entity);

        EntityEvent entityEvent = new EntityEvent();
        entityEvent.setEntityName("1.1.1.1");
        entityEvent.setEntityType("ip");
        entityEvent.setType(EntityEventType.ENTITY_ADD_OR_UPDATE);
        List<EntityEvent> list = new ArrayList<>();
        list.add(entityEvent);

        return list;
    }

    @Override
    public Entity getEntity(String entityName, String entityType) {
        return entityMap.get(entityType).get(entityName);
    }
}
