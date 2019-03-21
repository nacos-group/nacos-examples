1. 下载Nacos安装包：https://github.com/alibaba/nacos/releases
2. 本地解压
3. 设置CMDB和EVENT模块日志级别为DEBUG
  {nacos.home}/conf/nacos-logback.xml
4. 将CMDB文件放到特定目录
5. 设置-D参数nacos.cmdb.file.path
6. 设置JAVA_HOME
7. 设置nacos.cmdb.loadDataAtStart=true
8. 将CMDB插件代码工程下载到本地，切到分支0321bj, 进行打包，然后放到plugins目录，把原来目录里存在的jar包删除
9. 启动Nacos Server
10. 在控制台上创建服务test.1，并配置规则CONSUMER.label.label2 = PROVIDER.label.label2
11. 启动Provide代码
12. 启动Consumer代码
13. 设置com.alibaba.nacos.client.naming.local.ip重启Consumer
14. 修改CMDB文件，查看订阅结果