package com.conexemi.emi.controller;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("id/{idCategory}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer idCategory) {
        Optional<Category> category = categoryService.getCategoryById(idCategory);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{nameCategory}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable CategoryType nameCategory) {
        Optional<Category> category = categoryService.getCategoryByName(nameCategory);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> category = categoryService.getAllCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
