package com.conexemi.emi.controller;

import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.RoleType;
import com.conexemi.emi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/id/{idRole}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer idRole) {
        Optional<Role> role = roleService.getRoleById(idRole);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{nameRole}")
    public ResponseEntity<Role> getRoleByName(@PathVariable RoleType nameRole) {
        Optional<Role> role = roleService.getRoleByName(nameRole);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
