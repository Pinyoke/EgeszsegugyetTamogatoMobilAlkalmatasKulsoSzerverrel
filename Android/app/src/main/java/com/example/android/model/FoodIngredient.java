package com.example.android.model;

public class FoodIngredient {

    private String type;

    private String name;

    private Boolean alergen;

    public FoodIngredient() {
    }

    public FoodIngredient(String type, String name, Boolean alergen) {
        this.type = type;
        this.name = name;
        this.alergen = alergen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAlergen() {
        return alergen;
    }

    public void setAlergen(Boolean alergen) {
        this.alergen = alergen;
    }

    @Override
    public String toString() {
        return name;
    }
}
