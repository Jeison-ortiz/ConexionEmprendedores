package com.conexemi.emi.controller;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> category = categoryService.getAllCategories();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
