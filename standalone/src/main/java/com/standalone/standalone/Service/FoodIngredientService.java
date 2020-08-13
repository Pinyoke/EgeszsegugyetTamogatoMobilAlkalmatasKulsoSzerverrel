package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.FoodIngredient;

public interface FoodIngredientService {

    FoodIngredient save(FoodIngredient foodIngredient);

    FoodIngredient findByName(String name);

    boolean existFoodIngredientByName(String name);
}
