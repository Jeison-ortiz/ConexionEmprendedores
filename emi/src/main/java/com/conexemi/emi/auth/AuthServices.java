package com.conexemi.emi.auth;

import com.conexemi.emi.DTO.UserDTO;
import com.conexemi.emi.JWT.JWTService;
import com.conexemi.emi.model.City;
import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.CityRepository;
import com.conexemi.emi.repositories.RoleRepository;
import com.conexemi.emi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getUserPassword()));
        // Upload user details
        UserDetails user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Generate token and return response
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public String register(UserDTO request) {
        // Create User instance
        User user = new User();
        // Map data from DTO to User object
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        // Assign city based on city ID
        City city = cityRepository.findById(request.getIdCity())
                .orElseThrow(() -> new RuntimeException("City not found"));  // Buscar la ciudad por ID
        user.setIdCity(city);
        // Assign roles (this may require additional processing if roles are managed with IDs)
        List<Role> roles = roleRepository.findAllById(request.getIdRoles());
        user.setRoles(roles);

        //Save user to database
        userRepository.save(user);
        // Return response
        return "Usuario registrado con éxito.";
    }



}
