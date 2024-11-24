package com.conexemi.emi.controller;

import com.conexemi.emi.model.City;
import com.conexemi.emi.model.Role;
import com.conexemi.emi.services.CityService;
import com.conexemi.emi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

  @Autowired
  CityService cityService;

  @GetMapping
  public ResponseEntity<List<City>> getAllCities() {
    List<City> cities = cityService.findAllCities();
    System.out.println(cities);
    return new ResponseEntity<>(cities, HttpStatus.OK);
  }

}
