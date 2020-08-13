package com.springserver.server.repository;

import com.springserver.server.model.FoodIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodIngredientRepository extends JpaRepository<FoodIngredient,Long> {
    boolean existsFoodIngredientByName(String name);

    FoodIngredient findByName(String name);
}
