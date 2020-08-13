package com.example.android.model;

import androidx.annotation.NonNull;

public class Sensitivity {

    Long id;

    String myType;

    String description;

    Allergen allergen;

    public Sensitivity() {
    }

    public Sensitivity(Long id, String myType, String description, Allergen allergen) {
        this.id = id;
        this.myType = myType;
        this.description = description;
        this.allergen = allergen;
    }

    public String getMyType() {
        return myType;
    }

    public void setMyType(String myType) {
        this.myType = myType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Allergen getAllergen() {
        return allergen;
    }

    public void setAllergen(Allergen allergen) {
        this.allergen = allergen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return allergen.getName();
    }
}
