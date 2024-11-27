package com.conexemi.emi.services;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Optional<Category> getCategoryById(Integer idCategory) {
        return categoryRepository.findById(idCategory);
    }

    public Optional<Category> getCategoryByName(CategoryType nameCategory) {
        return categoryRepository.findByNameCategory(nameCategory);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


}
