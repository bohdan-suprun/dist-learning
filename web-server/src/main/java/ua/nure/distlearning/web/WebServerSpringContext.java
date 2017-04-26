package ua.nure.distlearning.web;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.epam.cdp.epambook.PersistenceSpringContext;
import ua.nure.distlearning.SpringSecurityApplicationContext;

/**
 * @author Bohdan_Suprun
 */
@Configuration
@ComponentScan("com.epam.cdp.epambook.web")
@EnableWebMvc
@Import({PersistenceSpringContext.class, SpringSecurityApplicationContext.class})
public class WebServerSpringContext {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
