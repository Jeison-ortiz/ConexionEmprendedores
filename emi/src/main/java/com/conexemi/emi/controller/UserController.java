package com.conexemi.emi.controller;

import com.conexemi.emi.model.User;
import com.conexemi.emi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable Integer idUser) {
        Optional<User> user = userService.getUserById(idUser);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idUser}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer idUser) {
        userService.deleteUserById(idUser);
        return ResponseEntity.noContent().build();
    }


}
