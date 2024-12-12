package com.conexemi.emi.config;

import com.conexemi.emi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;


    // Configures and provides an AuthenticationManager instance.
    // AuthenticationManager is responsible for managing the authentication process
    // (for example, verify credentials and user authentication).
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    // Configure an AuthenticationProvider.
    // An AuthenticationProvider is responsible for authenticating a specific type of data
    // user, in this case by checking with a UserDetailsService
    // and a password encoder (PasswordEncoder).
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    // Provides a custom UserDetailsService.
    // This service is used to load user information from the database
    // depending on your email. If the user does not exist, throw an exception.
    @Bean
    public UserDetailsService userDetailService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    // Set up the password scrambler.
    // BCryptPasswordEncoder is a password encryption implementation
    // which uses a secure salted hash to store passwords securely.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
