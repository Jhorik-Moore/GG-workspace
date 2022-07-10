package com.ggworkspace.admin.application.configuration;

import com.ggworkspace.admin.domain.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.conf.admin.username}")
    private String userNameAdmin;
    @Value("${spring.security.conf.admin.password}")
    private String passwordAdmin;
    @Value("${spring.security.conf.oper.username}")
    private String userNameOper;
    @Value("${spring.security.conf.oper.password}")
    private String passwordOper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/gg-bank.ua").permitAll()
                .antMatchers("/workspace.task.ua").hasAnyRole(Role.ADMIN.name(), Role.OPER.name())
                .antMatchers( "/workspace.task.ua/edit_task/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username(userNameAdmin)
                        .password(passwordEncoder().encode(passwordAdmin))
                        .roles(Role.ADMIN.name())
                        .build(),
                User.builder()
                        .username(userNameOper)
                        .password(passwordEncoder().encode(passwordOper))
                        .roles(Role.OPER.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
