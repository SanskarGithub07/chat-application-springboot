package com.sanskar.chat.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/register", "/", "/users", "/verifyRegistration"
    };
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable()) // Disable CORS (if not needed)
                .csrf(csrf -> csrf.disable()) // Disable CSRF (if appropriate)
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(WHITE_LIST_URLS).permitAll() // Allow access to whitelisted URLs
                        .anyRequest().permitAll() // Secure all other URLs
                );

        return http.build();
    }
}
