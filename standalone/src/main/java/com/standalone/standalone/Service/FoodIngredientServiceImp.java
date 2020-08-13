package com.standalone.standalone.Service;

import com.standalone.standalone.Entity.FoodIngredient;
import com.standalone.standalone.Repository.FoodIngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodIngredientServiceImp implements FoodIngredientService{

    private final FoodIngredientRepository foodIngredientRepository;

    public FoodIngredientServiceImp(FoodIngredientRepository foodIngredientRepository) {
        this.foodIngredientRepository = foodIngredientRepository;
    }

    @Override
    public FoodIngredient save(FoodIngredient foodIngredient) {
        return foodIngredientRepository.save(foodIngredient);
    }

    @Override
    public FoodIngredient findByName(String name) {
        return foodIngredientRepository.findByName(name);
    }

    @Override
    public boolean existFoodIngredientByName(String name) {
        return foodIngredientRepository.existsFoodIngredientByName(name);
    }
}
