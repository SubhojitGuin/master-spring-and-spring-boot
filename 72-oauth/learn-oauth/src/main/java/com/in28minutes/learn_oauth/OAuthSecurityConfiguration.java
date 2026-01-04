package com.in28minutes.learn_oauth;

import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * For OAuth client ID and client secret, create the credentials from Google API console (console.cloud.google.com)
 * Add `<a href="http://localhost:8080/login/oauth2/code/google">http://localhost:8080/login/oauth2/code/google</a>` as the Authorized redirected URI
 *
 */
@Configuration
public class OAuthSecurityConfiguration {

    @Bean
    @Order(SecurityFilterProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
        http.oauth2Login(withDefaults());
        return http.build();
    }

}
