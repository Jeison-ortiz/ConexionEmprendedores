package com.conexemi.emi.Mapper;

import com.conexemi.emi.DTO.EntrepreneurshipDTO;
import com.conexemi.emi.Exceptions.InvalidDataException;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.model.Category;
import com.conexemi.emi.model.City;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.model.User;
import com.conexemi.emi.repositories.CategoryRepository;
import com.conexemi.emi.repositories.CityRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntrepreneurshipMapper {

    // Convert from Entity to DTO
    public static EntrepreneurshipDTO toDTO(Entrepreneurship entrepreneurship) {
        EntrepreneurshipDTO dto = new EntrepreneurshipDTO();
        dto.setIdEntrepreneurship(entrepreneurship.getIdEntrepreneurship());
        dto.setEntrepreneurshipName(entrepreneurship.getEntrepreneurshipName());
        dto.setEntrepreneurshipDescription(entrepreneurship.getEntrepreneurshipDescription());
        dto.setImage(entrepreneurship.getImage());
        dto.setAddress(entrepreneurship.getAddress());
        dto.setIdCity(entrepreneurship.getIdCity().getIdCity());
        dto.setIdUser(entrepreneurship.getIdUser().getIdUser());
        dto.setIdCategories(entrepreneurship.getCategories().stream().map(Category::getIdCategory).collect(Collectors.toList()));

        return dto;
    }

    // Convert from DTO to Entity
    public static Entrepreneurship toEntity(EntrepreneurshipDTO entrepreneurshipDTO,
                                            CityRepository cityRepository,
                                            UserRepository userRepository,
                                            CategoryRepository categoryRepository) {

        // Search City by ID
        City city = cityRepository.findById(entrepreneurshipDTO.getIdCity())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "City with ID " + entrepreneurshipDTO.getIdCity() + " not found"));

        // Search User by ID
        User user = userRepository.findById(entrepreneurshipDTO.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID " + entrepreneurshipDTO.getIdUser() + " not found"));

        // Search Categories by their IDs
        List<Category> categories = categoryRepository.findAllById(entrepreneurshipDTO.getIdCategories());
        if (categories.isEmpty()) {
            throw new InvalidDataException("No valid categories found for the provided IDs");
        }

        // Create the entity
        Entrepreneurship entrepreneurship = new Entrepreneurship();
        entrepreneurship.setEntrepreneurshipName(entrepreneurshipDTO.getEntrepreneurshipName());
        entrepreneurship.setEntrepreneurshipDescription(entrepreneurshipDTO.getEntrepreneurshipDescription());
        entrepreneurship.setImage(entrepreneurshipDTO.getImage());
        entrepreneurship.setAddress(entrepreneurshipDTO.getAddress());
        entrepreneurship.setIdCity(city);
        entrepreneurship.setIdUser(user);
        entrepreneurship.setCategories(categories);

        return entrepreneurship;
    }


}
