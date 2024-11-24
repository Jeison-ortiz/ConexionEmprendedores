package com.conexemi.emi.services;

import com.conexemi.emi.model.City;
import com.conexemi.emi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

  @Autowired
  CityRepository cityRepository;

  public List<City> findAllCities(){
    return cityRepository.findAll();
  }

}
