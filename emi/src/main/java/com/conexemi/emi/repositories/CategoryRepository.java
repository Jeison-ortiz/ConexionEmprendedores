package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByNameCategory(CategoryType nameCategory);

}
