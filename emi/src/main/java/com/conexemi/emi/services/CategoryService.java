package com.conexemi.emi.services;

import com.conexemi.emi.Exceptions.ResourceNotFoundException;
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
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + idCategory + " not found")));
        return category;
    }

    public Optional<Category> getCategoryByName(CategoryType nameCategory) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByNameCategory(nameCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Category with NAME " + nameCategory + " not found")));
        return category;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


}
