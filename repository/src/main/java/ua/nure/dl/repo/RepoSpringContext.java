package ua.nure.dl.repo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Bohdan_Suprun
 */
@EntityScan(basePackages = {"ua.nure.dl.model"})
@SpringBootApplication
public class RepoSpringContext {
}
