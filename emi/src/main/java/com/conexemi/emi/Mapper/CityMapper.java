package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.CityDTO;
import com.conexemi.emi.model.City;

public class CityMapper {

    // Convert from Entity to DTO
    public static CityDTO toDTO(City city) {
        if (city == null) {
            return null;
        }
        return new CityDTO(
                city.getIdCity(),
                city.getCityName());
    }

    // Convert from DTO to Entity
    public static City toEntity(CityDTO cityDTO) {
        if (cityDTO == null) {
            return null;
        }
        City city = new City();
        city.setIdCity(cityDTO.getIdCity());
        city.setCityName(cityDTO.getCityName());
        return city;
    }

}
