package com.alibaba.nacos.health.ext;

import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.nacos.api.naming.pojo.AbstractHealthChecker;

/**
 * @author XCXCXCXCX
 * @since 1.0
 */
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
