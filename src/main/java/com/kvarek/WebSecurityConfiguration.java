package com.kvarek;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kvarek.model.Person;
import com.kvarek.service.PersonServiceImpl;
import com.kvarek.validator.JsonObjectAuthenticationFilter;
import com.kvarek.validator.RestAuthenticationFailureHandler;
import com.kvarek.validator.RestAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;
    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;

    @Autowired
    PersonServiceImpl personServiceImpl;

    Person person;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


 /*   public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .withDefaultSchema()
                .dataSource(dataSource);
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
               // .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/saveCoach", "/login").permitAll()
                //.antMatchers(HttpMethod.DELETE).permitAll()
                //.antMatchers(HttpMethod.PUT).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter())
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationManager(super.authenticationManager());
        return authenticationFilter;
    }

}
