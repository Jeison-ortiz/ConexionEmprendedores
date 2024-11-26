package com.conexemi.emi.services;

import com.conexemi.emi.model.City;
import com.conexemi.emi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> getCityById(Integer idCity) {
        return cityRepository.findById(idCity);
    }

    public Optional<City> getCityByName(String nameCity) {
        return cityRepository.findByCityName(nameCity);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


}
