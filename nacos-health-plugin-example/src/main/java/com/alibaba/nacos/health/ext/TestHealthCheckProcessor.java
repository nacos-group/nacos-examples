package com.alibaba.nacos.health.ext;

import com.alibaba.nacos.naming.healthcheck.HealthCheckProcessor;
import com.alibaba.nacos.naming.healthcheck.HealthCheckTask;

/**
 *
 * @author XCXCXCXCX
 * @since 1.0
 */
public class TestHealthCheckProcessor implements HealthCheckProcessor{

    /**
     * Run check task for service
     *
     * @param task check task
     */
    @Override
    public void process(HealthCheckTask task) {
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
