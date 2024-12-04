package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.UpdateUserDTO;
import com.conexemi.emi.DTO.UserDTO;
import com.conexemi.emi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO saveUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(saveUserDTO, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer idUser) {
        Optional<UserDTO> userDTO = userService.getUserById(idUser);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = userService.getAllUsers();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @PatchMapping("/id/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer idUser, @RequestBody UpdateUserDTO updateUserDTO) {
        UserDTO updatedUserDTO = userService.updateUser(idUser, updateUserDTO);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idUser}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer idUser) {
        userService.deleteUserById(idUser);
        return ResponseEntity.noContent().build();
    }


}
