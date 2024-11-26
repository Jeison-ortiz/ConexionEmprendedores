package com.conexemi.emi.repositories;

import com.conexemi.emi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
