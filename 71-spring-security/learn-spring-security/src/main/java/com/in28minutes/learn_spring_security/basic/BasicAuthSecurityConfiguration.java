package com.in28minutes.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BasicAuthSecurityConfiguration {

    enum Role {
        USER,
        ADMIN
    }

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
//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    // This allows storing multiple users InMemory (non-persistent)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("Subho")
                .password("{noop}dummy") // Since, the password is not encoded, we use {noop}
                .roles(Role.USER.name())
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}dummy")
                .roles(Role.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
