package com.example.miniproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // This means that you need to be authorized in order to access any of these endpoints in the main Controller class
public class SecurityConfiguration {

    @Bean // In order to use the functionality of this method, you must make it a bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
