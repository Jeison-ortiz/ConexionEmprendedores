package com.conexemi.emi.services;

import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.RoleType;
import com.conexemi.emi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getRoleById(Integer idRole) {
        return roleRepository.findById(idRole);
    }

    public Optional<Role> getRoleByName(RoleType nameRole) {
        return roleRepository.findByNameRole(nameRole);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
