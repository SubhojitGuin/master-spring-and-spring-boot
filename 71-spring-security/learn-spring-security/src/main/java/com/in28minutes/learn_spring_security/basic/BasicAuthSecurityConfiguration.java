package com.in28minutes.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableMethodSecurity // This enables method specific security (@PreAuthorize and @PostAuthorize)
//@EnableMethodSecurity(jsr250Enabled = true) // This enables jsr250 annotations over the methods for security
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true) // securedEnabled = true enables @Secured annotation over the methods for security
public class BasicAuthSecurityConfiguration {

    enum Role {
        USER,
        ADMIN
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(
                auth -> {
                    auth
                    .requestMatchers("/users/**").hasRole("USER") // Configure access to the resource based on the ROLE globally
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated(); // Authorizes any incoming requests
                });
        http.sessionManagement(
                session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Make the session stateless by not creating any session
                });
//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(
                headers -> headers.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin));  // To enable frames from the same origin
        return http.build();
    }

    // This allows storing multiple users InMemory (non-persistent)
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("Subho")
//                .password("{noop}dummy") // Since, the password is not encoded, we use {noop}
//                .roles(Role.USER.name())
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}dummy")
//                .roles(Role.ADMIN.name())
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    //  Provides a datasource to store the User details
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) // Sets the Embedded database as H2
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION) // Executes the ddl for creating the Users and Authorities Database
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails user = User.withUsername("Subho")
//                .password("{noop}dummy") // Since, the password is not encoded, we use {noop}
                .password("dummy")
                .passwordEncoder(password -> passwordEncoder().encode(password))
                .roles(Role.USER.name())
                .build();

        UserDetails admin = User.withUsername("admin")
//                .password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(password -> passwordEncoder().encode(password))
                .roles(Role.ADMIN.name(), Role.USER.name())
                .build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
