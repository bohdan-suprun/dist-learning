package com.epam.cdp.epambook.discovery.servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceConfig {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String DISCOVERY_SERVER = "discovery-server";

    public static void main(String[] args) {
        System.setProperty(SPRING_PROPERTY_NAME, DISCOVERY_SERVER);

        SpringApplication.run(DiscoveryServiceConfig.class, args);
    }
}
