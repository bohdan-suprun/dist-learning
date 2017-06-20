package ua.nure.dl.competencymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ua.nure.dl.model.util.EntityToDtoConverter;
import ua.nure.dl.repo.RepoSpringContext;

/**
 * @author Bohdan_Suprun
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2
public class MicroserviceCompetencySpringContext {
    private static final String SPRING_PROPERTY_NAME = "spring.config.name";
    private static final String SERVER_NAME = "competency-subj-service";

    @Bean
    public EntityToDtoConverter converter() {
        return new EntityToDtoConverter();
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public static void main(String[] args) {
        System.setProperty(SPRING_PROPERTY_NAME, SERVER_NAME);

        new SpringApplicationBuilder()
                .sources(MicroserviceCompetencySpringContext.class, RepoSpringContext.class)
                .run(args);
    }
}
