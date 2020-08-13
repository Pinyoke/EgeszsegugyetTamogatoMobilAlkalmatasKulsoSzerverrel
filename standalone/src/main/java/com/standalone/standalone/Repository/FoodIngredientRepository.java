package com.standalone.standalone.Repository;


import com.standalone.standalone.Entity.FoodIngredient;
import org.springframework.data.repository.CrudRepository;

public interface FoodIngredientRepository extends CrudRepository<FoodIngredient, Long> {

    boolean existsFoodIngredientByName(String name);

    FoodIngredient findByName(String name);
}
