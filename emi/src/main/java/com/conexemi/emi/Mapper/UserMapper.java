package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.UserDTO;
import com.conexemi.emi.model.City;
import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.CityRepository;
import com.conexemi.emi.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert from Entity to DTO
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobile(),
                null,
                user.getIdCity() != null ? user.getIdCity().getIdCity() : null,
                user.getRoles() != null
                        ? user.getRoles().stream().map(Role::getIdRole).collect(Collectors.toList())
                        : null
        );
    }

    //Convert from DTO to Entity
    public static User toEntity(UserDTO userDTO, CityRepository cityRepository, RoleRepository roleRepository) {
        // Get related city
        City city = cityRepository.findById(userDTO.getIdCity())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        // Get related roles
        List<Role> roles = roleRepository.findAllById(userDTO.getIdRoles());

        //Create the User entity
        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setUserPassword(userDTO.getUserPassword());
        user.setIdCity(city);
        user.setRoles(roles);
        return user;
    }

}
