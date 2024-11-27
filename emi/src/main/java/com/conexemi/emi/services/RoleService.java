package com.conexemi.emi.services;

import com.conexemi.emi.Exceptions.ResourceNotFoundException;
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
        Optional<Role> role = Optional.ofNullable(roleRepository.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + idRole + " not found")));
        return role;
    }

    public Optional<Role> getRoleByName(RoleType nameRole) {
        Optional<Role> role = Optional.ofNullable(roleRepository.findByNameRole(nameRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role with NAME " + nameRole + " not found")));
        return role;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


}
