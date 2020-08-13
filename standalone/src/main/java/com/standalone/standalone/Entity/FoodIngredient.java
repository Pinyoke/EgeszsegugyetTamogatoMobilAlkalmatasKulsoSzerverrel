package com.standalone.standalone.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="foodIngredients")
public class FoodIngredient extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String type;

    private String name;

    private Boolean alergen;

    public FoodIngredient() {
    }


    public FoodIngredient(Product product, String type, String name, Boolean alergen) {
        this.product = product;
        this.type = type;
        this.name = name;
        this.alergen = alergen;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
