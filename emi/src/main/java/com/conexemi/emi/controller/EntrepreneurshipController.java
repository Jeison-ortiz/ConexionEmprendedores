package com.conexemi.emi.controller;

import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.services.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrepreneurship")
public class EntrepreneurshipController {

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;


    @PostMapping
    public ResponseEntity<Entrepreneurship> createEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship) {
        Entrepreneurship saveEntrepreneurship = entrepreneurshipService.createEntrepreneurship(entrepreneurship);
        return new ResponseEntity<>(saveEntrepreneurship, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<Entrepreneurship> getEntrepreneurshipById(@PathVariable Integer idEntrepreneurship) {
        Optional<Entrepreneurship> entrepreneurship = entrepreneurshipService.getEntrepreneurshipById(idEntrepreneurship);
        return entrepreneurship.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Entrepreneurship>> getAllEntrepreneurships() {
        List<Entrepreneurship> entrepreneurship = entrepreneurshipService.getAllEntrepreneurships();
        return new ResponseEntity<>(entrepreneurship, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<Void> deleteEntrepreneurshipById(@PathVariable Integer idEntrepreneurship) {
        entrepreneurshipService.deleteEntrepreneurshipById(idEntrepreneurship);
        return ResponseEntity.noContent().build();
    }


}
