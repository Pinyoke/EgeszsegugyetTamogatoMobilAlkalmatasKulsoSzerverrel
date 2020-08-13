package com.standalone.standalone.Repository;

import com.standalone.standalone.Entity.Allergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen,Long> {

    Allergen findByName(String name);

}
