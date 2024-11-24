package com.conexemi.emi.controller;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> category = categoryService.findAllCategory();
    System.out.println(category);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
}
