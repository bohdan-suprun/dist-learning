package ua.nure.distlearning;

import ua.nure.distlearning.security.JsonUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Bohdan_Suprun
 */
@EnableWebSecurity
@Configuration
@ComponentScan("com.epam.cdp.epambook.security")
public class SpringSecurityApplicationContext extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JsonUsernamePasswordAuthenticationFilter authenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                // Replace default post parameter filter
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/users/*/**")
                        .access("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
                    .antMatchers("/users/*/**")
                        .access("hasRole('ROLE_USER')")
                    .antMatchers("/users")
                        .access("hasRole('ROLE_ANONYMOUS')")
        .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logout.success")
        .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/login.success")
                    .defaultSuccessUrl("/login.success", true);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

        auth.userDetailsService(userDetailsService);
    }
}
