package com.in28minutes.learn_spring_security.resources;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityPlayResource {

    @GetMapping("/csrf-token")
    public CsrfToken retrieveCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}

// CSRF is passed with the request as a part of the header
// This makes the REST API stateful, as the server maintains a state to verify the csrf token
// since the CSRF token is tied to the session cookie
// But if the REST API is purely stateless, then there is no need to worry about CSRF, as it won't be a part of it, so it has to be disabled