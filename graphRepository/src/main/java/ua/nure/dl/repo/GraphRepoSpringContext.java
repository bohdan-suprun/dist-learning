package ua.nure.dl.repo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

/**
 * @author Bohdan_Suprun
 */
@SpringBootApplication
public class GraphRepoSpringContext {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory()
                .newEmbeddedDatabase("/tmp/neo4j");
    }
}
