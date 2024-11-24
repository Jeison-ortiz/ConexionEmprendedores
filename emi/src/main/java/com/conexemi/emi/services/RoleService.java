package com.conexemi.emi.services;

import com.conexemi.emi.model.Role;
import com.conexemi.emi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  public List<Role> findAllRoles(){
    return roleRepository.findAll();
  }

}
