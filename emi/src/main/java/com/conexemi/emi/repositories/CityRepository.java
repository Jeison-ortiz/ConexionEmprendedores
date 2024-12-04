package com.conexemi.emi.repositories;

import com.conexemi.emi.model.City;
import com.conexemi.emi.model.CityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByNameCity(CityType nameCity);

}
