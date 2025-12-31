package com.in28minutes.rest.webservices.restful_web_services.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        // Authenticate all requests
        return http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults()) // Show a basic popup for authentication for unauthenticated request
        .sessionManagement( // Ensures stateless rest api
            session -> session.sessionCreationPolicy
                (SessionCreationPolicy.STATELESS))
        .csrf(AbstractHttpConfigurer::disable) // Disable csrf for POST and PUT requests
        .build();
    }
}
