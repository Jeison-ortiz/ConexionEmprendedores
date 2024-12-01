package com.conexemi.emi.controller;

import com.conexemi.emi.DTO.CityDTO;
import com.conexemi.emi.model.CityType;
import com.conexemi.emi.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {

    @Autowired
    private CityService cityService;


    @PostMapping
    public ResponseEntity<CityDTO> createCity(@Valid @RequestBody CityDTO cityDTO) {
        CityDTO saveCityDTO = cityService.createCity(cityDTO);
        return new ResponseEntity<>(saveCityDTO, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idCity}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Integer idCity) {
        Optional<CityDTO> cityDTO = cityService.getCityById(idCity);
        return cityDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{nameCity}")
    public ResponseEntity<CityDTO> getCityByName(@PathVariable CityType nameCity) {
        Optional<CityDTO> cityDTO = cityService.getCityByName(nameCity);
        return cityDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> citiesDTO = cityService.getAllCities();
        return new ResponseEntity<>(citiesDTO, HttpStatus.OK);
    }


}
