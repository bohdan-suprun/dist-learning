package ua.nure.dl.competencymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ua.nure.dl.repo.GraphRepoSpringContext;
import ua.nure.dl.repo.RepoSpringContext;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
public class CompetencySpringContext {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CompetencySpringContext.class, RepoSpringContext.class, GraphRepoSpringContext.class)
                .run(args);
    }
}
