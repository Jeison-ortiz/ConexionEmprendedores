package com.conexemi.emi.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    // Secret key obtained from the system environment.
    // Used to sign the JWT and ensure that it has not been altered.
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");


    // Generate a JWT for a user.
    // This method calls the overloaded version passing an empty map for the 'extraClaims'.
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }


    // Generates a JWT with possible 'extraClaims' for a user.
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // Additional claims are established (empty by default).
                .setSubject(user.getUsername()) // The "subject" is the username of the authenticated user.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Issue date.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Expiration in 24 hours.
                .signWith(getKey(), SignatureAlgorithm.HS256) // Sign with the HMAC-SHA256 algorithm and the secret key.
                .compact(); // Generate the compact token.
    }


    // Gets the secret key to sign the JWT from the system environment variables.
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decode the secret key from Base64.
        return Keys.hmacShaKeyFor(keyBytes); // Generate an HMAC key to sign the JWT
    }


    // Gets the username from the JWT token.
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject); // Extract the 'subject' from the token.
    }


    // Validates whether a JWT token is valid for a user.
    // A token is valid if the username matches and if it is not expired.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token); // Extract the username from the token.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Check the token name and expiration.
    }


    // Gets all the claims from the JWT (additional information stored in the token).
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())  // Set the key to verify the signature.
                .build()
                .parseClaimsJws(token) // Parse the JWT token.
                .getBody(); // Extracts the body of the JWT (the claims).
    }


    // Obtains a specific value from the JWT claims, using a claims 'resolver'.
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token); // Gets all the claims of the token.
        return claimsResolver.apply(claims); // Applies the claims resolver to the claims.
    }


    // Gets the expiration date of the JWT.
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration); // Extract the expiration claim.
    }


    // Checks if the JWT token has expired.
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date()); // Compare the expiration date with the current date.
    }


}
