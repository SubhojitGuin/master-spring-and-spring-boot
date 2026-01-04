package com.in28minutes.learn_spring_security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class JwtSecurityConfiguration {

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
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())); // Enables OAuth2 Resource Server support
        http.headers(
                headers -> headers.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin));  // To enable frames from the same origin
        return http.build();
    }

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

    @Bean
    public KeyPair keyPair() { // 1: Create Key Pair
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) { // 2: Provide the RSA key object
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey); // 3a: Creates a new JWKSet
//        return new JWKSource<>() {
//            @Override
//            public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) {
//                return jwkSelector.select(jwkSet);
//            }
//        };
        return (jwkSelector, context) -> jwkSelector.select(jwkSet); // 3b: Creates a new JWKSource using the JWKSet
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException { // 4: Create the decoder
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) { // 5: Create the encoder
        return new NimbusJwtEncoder(jwkSource);
    }
}
