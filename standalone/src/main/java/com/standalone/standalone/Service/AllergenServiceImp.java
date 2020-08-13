package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Allergen;
import com.standalone.standalone.Repository.AllergenRepository;
import org.springframework.stereotype.Service;

@Service
public class AllergenServiceImp implements AllergenService{

    private final AllergenRepository allergenRepository;

    public AllergenServiceImp(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }

    @Override
    public Allergen save(Allergen allergen) {
        return allergenRepository.save(allergen);
    }

    @Override
    public Allergen getAllergenById(Long id) {
        return allergenRepository.findById(id).orElse(new Allergen());
    }

    @Override
    public Allergen findByName(String name) {
        return allergenRepository.findByName(name);
    }
}
