package ua.nure.dl.discovery.servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
@EnableZuulServer
@EnableDiscoveryClient
public class ApiServiceConfig {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String DISCOVERY_SERVER = "api-server";

    public static void main(String[] args) {
        System.setProperty(SPRING_PROPERTY_NAME, DISCOVERY_SERVER);

        SpringApplication.run(ApiServiceConfig.class, args);
    }
}
