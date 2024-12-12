package com.conexemi.emi.config;

import com.conexemi.emi.JWT.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter; // Custom filter to handle JWT authentication.
    private final AuthenticationProvider authProvider; // Authentication provider configured in ApplicationConfig.


    // Configures the security filter chain (SecurityFilterChain) for the application.
    // This setting defines how HTTP requests are handled based on security rules.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disables CSRF (Cross-Site Request Forgery) because a stateless (JWT) authentication scheme is used.
                .csrf(AbstractHttpConfigurer::disable)
                // Configure authorization rules.
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/**").permitAll() // Allows public access to routes starting with "/auth/".
                        .anyRequest().authenticated() // Require authentication for all other routes.
                )
                // Configure session management to be stateless, since JWT is being used.
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Registers the custom AuthenticationProvider to handle authentication.
                .authenticationProvider(authProvider)
                // Add the custom JWTAuthFilter filter before the UsernamePasswordAuthenticationFilter.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Build and return the configuration.
                .build();
    }

}
