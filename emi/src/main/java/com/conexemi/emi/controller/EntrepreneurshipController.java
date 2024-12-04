package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.EntrepreneurshipDTO;
import com.conexemi.emi.DTO.UpdateEntrepreneurshipDTO;
import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.services.EntrepreneurshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrepreneurship")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class EntrepreneurshipController {

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;


    @PostMapping
    public ResponseEntity<EntrepreneurshipDTO> createEntrepreneurship(@Valid @RequestBody EntrepreneurshipDTO entrepreneurshipDTO) {
        EntrepreneurshipDTO savedEntrepreneurshipDTO = entrepreneurshipService.createEntrepreneurship(entrepreneurshipDTO);
        return new ResponseEntity<>(savedEntrepreneurshipDTO, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<EntrepreneurshipDTO> getEntrepreneurshipById(@PathVariable Integer idEntrepreneurship) {
        Optional<EntrepreneurshipDTO> entrepreneurshipDTO = entrepreneurshipService.getEntrepreneurshipById(idEntrepreneurship);
        return entrepreneurshipDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EntrepreneurshipDTO>> getAllEntrepreneurships() {
        List<EntrepreneurshipDTO> entrepreneurshipsDTO = entrepreneurshipService.getAllEntrepreneurships();
        return new ResponseEntity<>(entrepreneurshipsDTO, HttpStatus.OK);
    }

    @PatchMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<EntrepreneurshipDTO> updateEntrepreneurship(@PathVariable Integer idEntrepreneurship, @RequestBody UpdateEntrepreneurshipDTO updateEntrepreneurshipDTO) {
        EntrepreneurshipDTO updatedEntrepreneurshipDTO = entrepreneurshipService.updateEntrepreneurship(idEntrepreneurship, updateEntrepreneurshipDTO);
        return new ResponseEntity<>(updatedEntrepreneurshipDTO, HttpStatus.OK);
    }

    @DeleteMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<Void> deleteEntrepreneurshipById(@PathVariable Integer idEntrepreneurship) {
        entrepreneurshipService.deleteEntrepreneurshipById(idEntrepreneurship);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{nameCategory}")
    public ResponseEntity<List<EntrepreneurshipDTO>> getEntrepreneurshipsByCategory(@PathVariable CategoryType nameCategory) {
        List<EntrepreneurshipDTO> entrepreneurshipsDTO = entrepreneurshipService.getEntrepreneurshipsByCategory(nameCategory);
        return new ResponseEntity<>(entrepreneurshipsDTO, HttpStatus.OK);
    }


}
