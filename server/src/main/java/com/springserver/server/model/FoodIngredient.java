package com.springserver.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="foodIngredients")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class FoodIngredient extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String type;

    private String name;

    private Boolean alergen;

    public FoodIngredient() {
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
