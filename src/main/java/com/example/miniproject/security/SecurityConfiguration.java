package com.example.miniproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // This means that you need to be authorized in order to access any of these endpoints in the main Controller class
public class SecurityConfiguration {

    @Bean // In order to use the functionality of this method, you must make it a bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtRequestFilter authJwtRequestFilter() {
        return new JwtRequestFilter();
    }


    /**
     * This sets the user's authorization
     *
     * @param http represents traffic
     * @return authorization for user's requests
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http represents traffic
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/users/", "/auth/users/login/", "/auth/users/register/")
                .permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // .antMatchers() means allow matching patterns
                .anyRequest().authenticated()
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .headers().frameOptions().disable();

        http.addFilterBefore(authJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
