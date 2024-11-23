package com.conexemi.emi.controller;

import com.conexemi.emi.model.Category;
import com.conexemi.emi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryRepository repository;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    List<Category> categories = repository.findAll();
    System.out.println(repository.findAll());
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }
}
