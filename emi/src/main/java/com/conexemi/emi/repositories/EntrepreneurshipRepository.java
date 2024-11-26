package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship, Integer> {

    Optional<Entrepreneurship> findByEntrepreneurshipName(String entrepreneurshipName);

}
