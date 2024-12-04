package com.conexemi.emi.services;

import com.conexemi.emi.DTO.UpdateUserDTO;
import com.conexemi.emi.DTO.UserDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.Mapper.UserMapper;
import com.conexemi.emi.model.City;
import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.CityRepository;
import com.conexemi.emi.repositories.RoleRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoleRepository roleRepository;


    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO, cityRepository, roleRepository);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Integer idUser) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + idUser + " not found")));
        return user.map(UserMapper::toDTO);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer idUser, UpdateUserDTO updateUserDTO) {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idUser));

        if (updateUserDTO.getFirstName() != null) {
            existingUser.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            existingUser.setLastName(updateUserDTO.getLastName());
        }
        if (updateUserDTO.getEmail() != null) {
            existingUser.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getMobile() != null) {
            existingUser.setMobile(updateUserDTO.getMobile());
        }
        if (updateUserDTO.getIdCity() != null) {
            City city = cityRepository.findById(updateUserDTO.getIdCity())
                    .orElseThrow(() -> new IllegalArgumentException("City not found"));
            existingUser.setIdCity(city);
        }
        if (updateUserDTO.getIdRoles() != null) {
            List<Role> roles = roleRepository.findAllById(updateUserDTO.getIdRoles());
            existingUser.setRoles(roles);
        }

        User savedUser = userRepository.save(existingUser);
        return UserMapper.toDTO(savedUser);
    }


    public void deleteUserById(Integer idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new ResourceNotFoundException("User with ID " + idUser + " not found");
        }
        userRepository.deleteById(idUser);
    }


}
