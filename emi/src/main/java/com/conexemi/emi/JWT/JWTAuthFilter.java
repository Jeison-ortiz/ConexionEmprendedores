package com.conexemi.emi.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {


    // This method overrides the doFilterInternal method of OncePerRequestFilter.
    // It is executed on each HTTP request to check if it contains a JWT token.
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {
        // Get the JWT token from the request
        final String token = getTokenFromRequest(request);
        // If there is no token, continue with the filter chain without doing anything
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        // If the token is present, it goes to the next filter (in this case we are not validating the token here)
        filterChain.doFilter(request, response);
    }


    // Method to get the JWT token from the HTTP request headers.
    private String getTokenFromRequest(HttpServletRequest request) {
        // Gets the 'Authorization' header of the request
        final String authHere = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Checks if the 'Authorization' header is present and if it starts with "Bearer"
        if (StringUtils.hasText(authHere) && authHere.startsWith("Bearer ")) {
            // If so, extract and return the token (after "Bearer")
            return authHere.substring(7);
        }
        // If it is not present or does not have the appropriate prefix, returns null
        return null;
    }


}
