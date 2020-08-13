package com.springserver.server.repository;

import com.springserver.server.model.Allergen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, Long> {

    List<Allergen> findAll();

    Page<Allergen> findByNameContaining(String name, Pageable pageable);
}
