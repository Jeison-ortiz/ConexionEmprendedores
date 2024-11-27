package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.EntrepreneurshipDTO;
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
    public ResponseEntity<EntrepreneurshipDTO> createEntrepreneurship(@RequestBody EntrepreneurshipDTO entrepreneurshipDTO) {
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

    @DeleteMapping("/id/{idEntrepreneurship}")
    public ResponseEntity<Void> deleteEntrepreneurshipById(@PathVariable Integer idEntrepreneurship) {
        entrepreneurshipService.deleteEntrepreneurshipById(idEntrepreneurship);
        return ResponseEntity.noContent().build();
    }


}
