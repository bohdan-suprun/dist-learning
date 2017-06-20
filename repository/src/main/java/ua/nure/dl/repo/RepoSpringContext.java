package ua.nure.dl.repo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Bohdan_Suprun
 */
@EntityScan(basePackages = {"ua.nure.dl.model.entity"})
@SpringBootApplication
@EnableJpaRepositories("ua.nure.dl.repo.relational")
@EnableTransactionManagement
//@Configuration
public class RepoSpringContext {
}
