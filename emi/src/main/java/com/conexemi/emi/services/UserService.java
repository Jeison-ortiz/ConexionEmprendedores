package com.conexemi.emi.services;

import com.conexemi.emi.DTO.UserDTO;
import com.conexemi.emi.Mapper.UserMapper;
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
        Optional<User> user = userRepository.findById(idUser);
        return user.map(UserMapper::toDTO);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteUserById(Integer idUser) {
        userRepository.deleteById(idUser);
    }

    public UserDTO updateUser(Integer idUser, UserDTO userDTO) {

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + idUser));

        Optional.ofNullable(userDTO.getFirstName()).ifPresent(existingUser::setFirstName);
        Optional.ofNullable(userDTO.getLastName()).ifPresent(existingUser::setLastName);
        Optional.ofNullable(userDTO.getEmail()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(userDTO.getMobile()).ifPresent(existingUser::setMobile);
        Optional.ofNullable(userDTO.getUserPassword()).ifPresent(existingUser::setUserPassword);

        Optional.ofNullable(userDTO.getIdCity()).ifPresent(idCity -> {
            existingUser.setIdCity(cityRepository.findById(idCity)
                    .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + idCity)));
        });

        Optional.ofNullable(userDTO.getIdRoles()).ifPresent(idRoles -> {
            existingUser.setRoles(roleRepository.findAllById(idRoles));
        });

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toDTO(updatedUser);
    }
}
