package ua.nure.dl.competencymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ua.nure.dl.model.util.EntityToDtoConverter;
import ua.nure.dl.repo.RepoSpringContext;

/**
 * @author Bohdan_Suprun
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class DistributedTestSpringContext {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String DISCOVERY_SERVER = "test-service";

    @Bean
    public EntityToDtoConverter converter() {
        return new EntityToDtoConverter();
    }

    public static void main(String[] args) {
        System.setProperty(SPRING_PROPERTY_NAME, DISCOVERY_SERVER);

        new SpringApplicationBuilder()
                .sources(DistributedTestSpringContext.class, RepoSpringContext.class)
                .run(args);
    }
}
