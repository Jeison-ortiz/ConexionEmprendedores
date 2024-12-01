package com.conexemi.emi.repositories;

import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.model.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship, Integer> {

    Optional<Entrepreneurship> findByEntrepreneurshipName(String entrepreneurshipName);

    @Query("SELECT e FROM Entrepreneurship e " +
            "JOIN e.categories c " +
            "WHERE c.nameCategory = :nameCategory")
    List<Entrepreneurship> getEntrepreneurshipsByCategory(@Param("nameCategory") CategoryType nameCategory);

}


