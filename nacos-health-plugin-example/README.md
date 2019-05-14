# How To Extend Health Check







## Step 1

* create your health check project 

eg.nacos-health-plugin-example

* add dependency

```xml
//Please correspond to the version currently in use
<dependency>
    <groupId>com.alibaba.nacos</groupId>
    <artifactId>nacos-naming</artifactId>
    <version>1.0.0</version>
</dependency>
```

Note: 1.0.0 was previously not supported for health check extensions.

* Construct your own implementation

Firstly, extend AbstractHealthChecker:

```java
public class Test extends AbstractHealthChecker{

    /**
     * Clone all fields of this instance to another one
     *
     * @return Another instance with exactly the same fields.
     * @throws CloneNotSupportedException
     */
    @Override
    public AbstractHealthChecker clone() throws CloneNotSupportedException {
        return new Test();
    }

    @Override
    public String getType() {
        return "test";
    }

    /**
     * used to JsonAdapter
     * By default, only write {"type":"test"} is used for serialization.
     * Since nacos uses a custom Serializer,
     * you must implement jsonAdapterCallback() to
     * customize the serialization result.
     *
     * @param writer
     */
    @Override
    public void jsonAdapterCallback(SerializeWriter writer) {
        //writer.writeFieldValue(',', "field1", "value1");
    }

}
```

secondly, implement HealthCheckProcessor:

```java
public class TestHealthCheckProcessor implements HealthCheckProcessor{

    /**
     * Run check task for service
     *
     * @param task check task
     */
    @Override
    public void process(HealthCheckTask task) {
        //use your own logic
        System.out.println("test process");
    }

    /**
     * Get check task type, refer to enum HealthCheckType
     *
     * @return check type
     */
    @Override
    public String getType() {
        return "test";
    }

}
```

Need to pay attention to the following points:
1. AbstractHealthChecker, HealthCheckProcessor must override the getType() method, and must be corresponding and unique.
2. There are some places in nacos that use the clone() method, so in order to ensure that your health check can run normally, you need to implement the clone() method to ensure that the field is correctly cloned.
3. nacos uses the custom serializer of fastjson, which handles the serialization and deserialization of AbstractHealthChecker separately. When implementing the new health check, you need to implement the jsonAdapterCallback(SerializeWriter) method to execute the serialization logic.

* Project packaged into jar

eg.The final jar package is nacos-health-plugin-example-1.0.jar
Note: Dependencies nacos-naming.jar should not be packaged together.


## Step 2

* Put the jar in Step1 into %NACOS_HOME%/plugins/health path

ps.%NACOS_HOME% refers to the nacos extraction path you downloaded

* startup Nacos

Refer to the official website startup mode, use the startup.sh or startup.cmd to start Nacos (depending on the system to choose different commands)



## Step 3

* enter the console page (http://localhost:8848/nacos)

A new health check type will appear when editing the cluster configuration

Note: The front end is still under development ! After step 2, the nacos server has been able to correctly handle the new health check method. After the development of the front-end part is completed, a new health check mode will be seen at the front end for the user to select.