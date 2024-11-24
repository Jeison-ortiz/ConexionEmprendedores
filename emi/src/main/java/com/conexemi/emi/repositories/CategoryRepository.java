package com.conexemi.emi.repositories;

import com.conexemi.emi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
