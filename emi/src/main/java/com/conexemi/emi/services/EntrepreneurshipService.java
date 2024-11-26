package com.conexemi.emi.services;

import com.conexemi.emi.model.Entrepreneurship;
import com.conexemi.emi.repositories.EntrepreneurshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    public Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship) {
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    public Optional<Entrepreneurship> getEntrepreneurshipById(Integer idEntrepreneurship) {
        return entrepreneurshipRepository.findById(idEntrepreneurship);
    }

    public List<Entrepreneurship> getAllEntrepreneurships() {
        return entrepreneurshipRepository.findAll();
    }

    public void deleteEntrepreneurshipById(Integer idEntrepreneurship) {
        entrepreneurshipRepository.deleteById(idEntrepreneurship);
    }


}
