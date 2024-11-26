package com.conexemi.emi.controller;

import com.conexemi.emi.model.City;
import com.conexemi.emi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City saveCity = cityService.createCity(city);
        return new ResponseEntity<>(saveCity, HttpStatus.CREATED);
    }

    @GetMapping("/id/{idCity}")
    public ResponseEntity<City> getCityById(@PathVariable Integer idCity) {
        Optional<City> city = cityService.getCityById(idCity);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{nameCity}")
    public ResponseEntity<City> getCityByName(@PathVariable String nameCity) {
        Optional<City> city = cityService.getCityByName(nameCity);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        System.out.println(cities);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
