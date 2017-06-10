package ua.nure.dl.competencymanager;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import ua.nure.dl.model.util.EntityToDtoConverter;

/**
 * @author Bohdan_Suprun
 */
@EnableDiscoveryClient
public class DistributedTestSpringContext {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String DISCOVERY_SERVER = "test-service";

    @Bean
    public EntityToDtoConverter converter() {
        return new EntityToDtoConverter();
    }

//    public static void main(String[] args) {
//        System.setProperty(SPRING_PROPERTY_NAME, DISCOVERY_SERVER);
//
//        new SpringApplicationBuilder()
//                .sources(DistributedTestSpringContext.class, RepoSpringContext.class)
//                .run(args);
//    }
}
