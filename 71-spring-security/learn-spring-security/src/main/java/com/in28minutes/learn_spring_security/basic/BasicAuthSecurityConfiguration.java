package com.in28minutes.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
			http.authorizeHttpRequests(
                    auth -> {
                        auth.anyRequest().authenticated(); // Authorizes any incoming requests
                    });
            http.sessionManagement(
                    session -> {
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Make the session stateless by not creating any session
                    });
//			http.formLogin(withDefaults());
			http.httpBasic(withDefaults());
            http.csrf(AbstractHttpConfigurer::disable);
            
			return http.build();
		}

}
