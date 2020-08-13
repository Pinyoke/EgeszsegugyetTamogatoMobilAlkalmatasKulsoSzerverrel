package com.example.android.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;

    private String gtin13;

    private String imageUrl;

    private List<FoodIngredient> foodIngredients = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String gtin13) {
        this.name = name;
        this.gtin13 = gtin13;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGtin13() {
        return gtin13;
    }

    public void setGtin13(String gtin13) {
        this.gtin13 = gtin13;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<FoodIngredient> getFoodIngredients() {
        return foodIngredients;
    }

    public void setFoodIngredients(List<FoodIngredient> foodIngredients) {
        this.foodIngredients = foodIngredients;
    }
}
