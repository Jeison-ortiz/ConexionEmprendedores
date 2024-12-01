package com.conexemi.emi.services;

import com.conexemi.emi.DTO.EntrepreneurshipDTO;
import com.conexemi.emi.DTO.UpdateEntrepreneurshipDTO;
import com.conexemi.emi.Exceptions.ResourceNotFoundException;
import com.conexemi.emi.Mapper.EntrepreneurshipMapper;
import com.conexemi.emi.model.CategoryType;
import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.repositories.CategoryRepository;
import com.conexemi.emi.repositories.CityRepository;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import com.conexemi.emi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepreneurshipMapper entrepreneurshipMapper;


    public EntrepreneurshipDTO createEntrepreneurship(EntrepreneurshipDTO entrepreneurshipDTO) {
        Entrepreneurship entrepreneurship = EntrepreneurshipMapper.toEntity(entrepreneurshipDTO, cityRepository, userRepository, categoryRepository);
        Entrepreneurship savedEntrepreneurship = entrepreneurshipRepository.save(entrepreneurship);
        return EntrepreneurshipMapper.toDTO(savedEntrepreneurship);
    }

    public Optional<EntrepreneurshipDTO> getEntrepreneurshipById(Integer idEntrepreneurship) {
        Optional<Entrepreneurship> entrepreneurship = Optional.ofNullable(entrepreneurshipRepository.findById(idEntrepreneurship)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneurship with ID " + idEntrepreneurship + " not found")));
        return entrepreneurship.map(EntrepreneurshipMapper::toDTO);
    }

    public List<EntrepreneurshipDTO> getAllEntrepreneurships() {
        List<Entrepreneurship> entrepreneurshipList = entrepreneurshipRepository.findAll();
        return entrepreneurshipList.stream().map(EntrepreneurshipMapper::toDTO).collect(Collectors.toList());
    }

    public EntrepreneurshipDTO updateEntrepreneurship(Integer idEntrepreneurship, UpdateEntrepreneurshipDTO updateEntrepreneurshipDTO) {
        Entrepreneurship existingEntrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneurship not found with id: " + idEntrepreneurship));

        if (updateEntrepreneurshipDTO.getEntrepreneurshipName() != null) {
            existingEntrepreneurship.setEntrepreneurshipName(updateEntrepreneurshipDTO.getEntrepreneurshipName());
        }
        if (updateEntrepreneurshipDTO.getEntrepreneurshipDescription() != null) {
            existingEntrepreneurship.setEntrepreneurshipDescription(updateEntrepreneurshipDTO.getEntrepreneurshipDescription());
        }
        if (updateEntrepreneurshipDTO.getImage() != null) {
            existingEntrepreneurship.setImage(updateEntrepreneurshipDTO.getImage());
        }
        if (updateEntrepreneurshipDTO.getAddress() != null) {
            existingEntrepreneurship.setAddress(updateEntrepreneurshipDTO.getAddress());
        }
        if (updateEntrepreneurshipDTO.getIdCity() != null) {
            existingEntrepreneurship.setIdCity(cityRepository.findById(updateEntrepreneurshipDTO.getIdCity())
                    .orElseThrow(() -> new ResourceNotFoundException("City not found")));
        }
        if (updateEntrepreneurshipDTO.getIdUser() != null) {
            existingEntrepreneurship.setIdUser(userRepository.findById(updateEntrepreneurshipDTO.getIdUser())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        }
        if (updateEntrepreneurshipDTO.getIdCategories() != null && !updateEntrepreneurshipDTO.getIdCategories().isEmpty()) {
            existingEntrepreneurship.setCategories(categoryRepository.findAllById(updateEntrepreneurshipDTO.getIdCategories()));
        }

        Entrepreneurship updatedEntrepreneurship = entrepreneurshipRepository.save(existingEntrepreneurship);
        return EntrepreneurshipMapper.toDTO(updatedEntrepreneurship);
    }


    public void deleteEntrepreneurshipById(Integer idEntrepreneurship) {
        entrepreneurshipRepository.deleteById(idEntrepreneurship);
    }

    public List<EntrepreneurshipDTO> getEntrepreneurshipsByCategory(CategoryType nameCategory) {
        List<Entrepreneurship> entrepreneurshipList = entrepreneurshipRepository.getEntrepreneurshipsByCategory(nameCategory);
        System.out.println("services");
        return entrepreneurshipList.stream()
                .map(entrepreneurship -> entrepreneurshipMapper.toDTO(entrepreneurship))
                .collect(Collectors.toList());
    }



}
