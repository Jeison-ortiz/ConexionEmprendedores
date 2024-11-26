package com.conexemi.emi.services;

import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer idUser) {
        return userRepository.findById(idUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Integer idUser) {
        userRepository.deleteById(idUser);
    }

}
