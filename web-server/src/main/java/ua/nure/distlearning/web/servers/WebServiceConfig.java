package ua.nure.distlearning.web.servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import ua.nure.distlearning.web.WebServerSpringContext;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(WebServerSpringContext.class)
public class WebServiceConfig {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String DISCOVERY_SERVER = "web-server";

    public static void main(String[] args) {
        System.setProperty(SPRING_PROPERTY_NAME, DISCOVERY_SERVER);

        SpringApplication.run(WebServiceConfig.class, args);
    }
}
