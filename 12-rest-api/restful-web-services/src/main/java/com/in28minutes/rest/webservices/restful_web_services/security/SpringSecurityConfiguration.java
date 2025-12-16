package com.in28minutes.rest.webservices.restful_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


// Overriding the default Security Filter Chain of Spring Security
@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // Ensure all requests are authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // If a request is not authenticated, then show a basic popup for authentication
        http.httpBasic(withDefaults());

        // To prevent blocking POST and PUT due to CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
