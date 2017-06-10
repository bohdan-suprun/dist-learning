package ua.nure.dl.competencymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
@SpringBootApplication
@EnableSwagger2
public class CompetencySpringContext {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CompetencySpringContext.class, RepoSpringContext.class)
                .run(args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public EntityToDtoConverter converter() {
        return new EntityToDtoConverter();
    }
}
