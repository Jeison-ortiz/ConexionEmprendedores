package com.conexemi.emi.services;

import com.conexemi.emi.DTO.EntrepreneurshipDTO;
import com.conexemi.emi.Mapper.EntrepreneurshipMapper;
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


    public EntrepreneurshipDTO createEntrepreneurship(EntrepreneurshipDTO entrepreneurshipDTO) {
        Entrepreneurship entrepreneurship = EntrepreneurshipMapper.toEntity(entrepreneurshipDTO, cityRepository, userRepository, categoryRepository);
        Entrepreneurship savedEntrepreneurship = entrepreneurshipRepository.save(entrepreneurship);
        return EntrepreneurshipMapper.toDTO(savedEntrepreneurship);
    }

    public Optional<EntrepreneurshipDTO> getEntrepreneurshipById(Integer idEntrepreneurship) {
        Optional<Entrepreneurship> entrepreneurship = entrepreneurshipRepository.findById(idEntrepreneurship);
        return entrepreneurship.map(EntrepreneurshipMapper::toDTO);
    }

    public List<EntrepreneurshipDTO> getAllEntrepreneurships() {
        List<Entrepreneurship> entrepreneurshipList = entrepreneurshipRepository.findAll();
        return entrepreneurshipList.stream()
                .map(EntrepreneurshipMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteEntrepreneurshipById(Integer idEntrepreneurship) {
        entrepreneurshipRepository.deleteById(idEntrepreneurship);
    }


}
