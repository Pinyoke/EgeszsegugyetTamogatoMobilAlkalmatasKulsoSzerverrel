package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.Allergen;

public interface AllergenService {

    Allergen save(Allergen allergen);

    Allergen getAllergenById(Long id);

    Allergen findByName(String name);
}
