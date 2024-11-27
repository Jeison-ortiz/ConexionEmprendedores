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


}
