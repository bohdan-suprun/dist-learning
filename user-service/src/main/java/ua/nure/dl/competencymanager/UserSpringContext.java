package ua.nure.dl.competencymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ua.nure.dl.model.entity.RoleEntity;
import ua.nure.dl.model.util.EntityToDtoConverter;
import ua.nure.dl.repo.RepoSpringContext;
import ua.nure.dl.repo.cache.UserRoleCache;
import ua.nure.dl.repo.cache.UserRoleCacheImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
@EnableSwagger2
public class UserSpringContext {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(UserSpringContext.class, RepoSpringContext.class)
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
    public UserRoleCache userRoleCache() {
        return new UserRoleCacheImpl();
    }

    @Bean
    public Set<RoleEntity> roleEntitySet() {
        Set<RoleEntity> roles = new HashSet<>();

        roles.add(new RoleEntity(1L, "ADMIN"));
        roles.add(new RoleEntity(2L, "STUDENT"));
        roles.add(new RoleEntity(3L, "TEACHER"));

        return roles;
    }

    @Bean
    public EntityToDtoConverter converter() {
        return new EntityToDtoConverter();
    }
}
