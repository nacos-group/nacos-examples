1. 下载Nacos安装包：https://github.com/alibaba/nacos/releases/download/1.0.0-RC1/nacos-server-1.0.0-RC1.zip
2. 本地解压，假设解压到目录/xxx，那么{nacos.home}就为/xxx/nacos
3. 设置CMDB和EVENT模块日志级别为DEBUG，文件位置：{nacos.home}/conf/nacos-logback.xml
4. 设置JAVA_HOME
5. 编辑{nacos.home}/conf/application.properties，设置nacos.cmdb.loadDataAtStart=true，开启CMDB功能
6. 将CMDB插件代码工程下载到本地，切到分支0321bj, 进入nacos-cmdb-plugin-example工程，进行打包: mvn package assembly:single -Dmaven.test.skip=true
7. 然后把nacos-cmdb-plugin-example/target下面的nacos-cmdb-plugin-example-0.2.0-SNAPSHOT-jar-with-dependencies.jar放到{nacos.home}/plugins/cmdb目录，把目录里原有的nacos-cmdb-plugin-example.jar包删除
7. 将nacos-cmdb-plugin-example工程根目录下的CMDB文件labels.txt, entities.txt放到指定目录
8. 设置-D参数nacos.cmdb.file.path，指向第7步中CMDB文件所在的目录
9. 启动Nacos Server：sh {nacos.home}/bin/startup.sh -m standalone
10. 在控制台上创建服务test.1，并配置规则CONSUMER.label.label2 = PROVIDER.label.label2
11. 启动Provide代码
12. 启动Consumer代码
13. 设置com.alibaba.nacos.client.naming.local.ip重启Consumer
14. 修改CMDB文件，查看订阅结果
