package com.alibaba.nacos.tapestry;

import org.apache.tapestry5.test.JettyRunner;

/**
 * Runs this webapp using Jetty in the 8080 port.
 */
public class RunWithJetty {
    
    public static void main(String[] args) throws Exception {
        new JettyRunner("src/main/webapp", "/", 8080, 8443);
    }

}
