package com.alibaba.nacos.example.cmdb.plugin;

import com.alibaba.nacos.api.cmdb.pojo.Entity;
import com.alibaba.nacos.api.cmdb.pojo.EntityEvent;
import com.alibaba.nacos.api.cmdb.pojo.Label;
import com.alibaba.nacos.api.cmdb.spi.CmdbService;

import java.io.*;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author nkorange
 */
public class FileCmdbServiceImpl implements CmdbService {

//    private Logger logger = LogFa

    private String cmdbFilePath = System.getProperty("nacos.cmdb.file.path");

    private static final ScheduledExecutorService CMDB_UDPATE_EXECUTOR
            = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("nacos.example.cmdb.updater");
            t.setDaemon(true);
            return t;
        }
    });

    private Map<String, Label> labelMap = new HashMap<>();

    private Map<String, Map<String, Entity>> entityMap = new HashMap<>();

    public FileCmdbServiceImpl() {

        CMDB_UDPATE_EXECUTOR.scheduleAtFixedRate(new CmdbInfoUpdater(), 0, 15, TimeUnit.SECONDS);
    }

    public class CmdbInfoUpdater implements Runnable {

        @Override
        public void run() {

            String labelFileName = "labels.txt";

            String entityFileName = "entities.txt";

            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdbFilePath + File.separator + labelFileName)));

                String line;

                while ((line = reader.readLine()) != null) {
                    String labelName = line.split(":")[0];
                    String[] labelValues = line.split(":")[1].split(",");
                    Label label = new Label();
                    label.setName(labelName);
                    label.setValues(new HashSet<>(Arrays.asList(labelValues)));
                    labelMap.put(labelName, label);
                }

            } catch (Exception e) {

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignore) {
                    }
                }
            }


            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdbFilePath + File.separator + entityFileName)));

                String line;

                entityMap.put("ip", new HashMap<String, Entity>());

                while ((line = reader.readLine()) != null) {
                    String entityName = line.split(":")[0];
                    String[] labels = line.split(":")[1].split(",");
                    Entity entity = new Entity();
                    entity.setType("ip");
                    entity.setName(entityName);
                    entity.setLabels(new HashMap<String, String>());
                    for (String label : labels) {
                        String labelName = label.split("=")[0];
                        String labelValue = label.split("=")[1];
                        entity.getLabels().put(labelName, labelValue);
                    }
                    entityMap.get("ip").put(entity.getName(), entity);
                }

            } catch (Exception e) {

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        }
    }

    @Override
    public Set<String> getLabelNames() {
        return labelMap.keySet();
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
    public String getLabelValue(String entityName, String entityType, String labelName) {
        return entityMap.get(entityType).get(entityName).getLabels().get(labelName);
    }

    @Override
    public Map<String, String> getLabelValues(String entityName, String entityType) {
        return entityMap.get(entityType).get(entityName).getLabels();
    }

    @Override
    public Map<String, Map<String, Entity>> getAllEntities() {
        return entityMap;
    }

    @Override
    public List<EntityEvent> getEntityEvents(long timestamp) {
        return null;
    }

    @Override
    public Entity getEntity(String entityName, String entityType) {
        return entityMap.get(entityType).get(entityName);
    }
}
