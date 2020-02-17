package com.example.projektKoncowy.configuration;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;
import java.util.Map;


@EnableGlobalMethodSecurity (securedEnabled = true)
@Configuration
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private static final Map<Integer, List<String>> USER_INDEX_TO_ROLES =
            Map.of(0, List.of("READ"),
                    1, List.of("READ", "ADD_OR_MODIFY"),
                    2, List.of("READ", "ADD_OR_MODIFY", "REMOVE"));


    private final UserConfiguration userConfiguration;

    public CustomSecurityConfiguration(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/design**").permitAll()
                .antMatchers(HttpMethod.POST, "/design**").hasRole("READ")
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADD_OR_MODIFY")
                .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADD_OR_MODIFY")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("REMOVE")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> buldier =
                auth.inMemoryAuthentication();
        for (int idx = 0; idx < userConfiguration.getUsernames().size(); idx++)
            buldier.withUser(userConfiguration.getUsernames().get(idx))
                    .password("{noop}" + userConfiguration.getPasswords().get(idx))
                    .roles(USER_INDEX_TO_ROLES.get(idx).get(idx))
                    .and();


    }
}