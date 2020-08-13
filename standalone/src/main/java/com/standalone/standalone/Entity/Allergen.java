package com.standalone.standalone.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Allergens")
public class Allergen  extends BaseEntity{

    private String name;


    public Allergen() {
    }

    public Allergen(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
