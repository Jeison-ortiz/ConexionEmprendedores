package com.conexemi.emi.repositories;

import com.conexemi.emi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
