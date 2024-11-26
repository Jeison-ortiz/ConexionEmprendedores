package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Role;
import com.conexemi.emi.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNameRole(RoleType nameRole);

}
