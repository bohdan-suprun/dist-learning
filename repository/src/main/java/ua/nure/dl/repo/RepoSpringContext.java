package ua.nure.dl.repo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * @author Bohdan_Suprun
 */
@EntityScan(basePackages = {"ua.nure.dl.model.entity"})
@SpringBootApplication
@EnableJpaRepositories("ua.nure.dl.repo.relational")
@EnableTransactionManagement
@Configuration
public class RepoSpringContext {
}
