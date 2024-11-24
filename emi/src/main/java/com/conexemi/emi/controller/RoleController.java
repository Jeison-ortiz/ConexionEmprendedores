package com.conexemi.emi.controller;

import com.conexemi.emi.model.Role;
import com.conexemi.emi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  RoleService roleService;

  @GetMapping
  public ResponseEntity<List<Role>> getAllRoles() {
    List<Role> roles = roleService.findAllRoles();
    System.out.println(roles);
    return new ResponseEntity<>(roles, HttpStatus.OK);
  }

}
