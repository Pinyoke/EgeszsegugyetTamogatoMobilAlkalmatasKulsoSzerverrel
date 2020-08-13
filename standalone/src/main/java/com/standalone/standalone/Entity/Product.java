package com.standalone.standalone.Entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="Products")
public class Product extends BaseEntity {

    @JsonProperty("offers")
    @Transient
    private Offer offer;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @Column(unique = true)
    private String gtin13;

    private String description;

    private String name;

    private String sku;

    private String imageUrl;

    @Transient
    @JsonProperty("image")
    private List<String> imageUrls;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodIngredient> foodIngredients;

    public Product() {
    }

    public Product(Offer offer, List<Offer> offers, String gtin13, String description, String name, String sku, String imageUrl, List<String> imageUrls, List<FoodIngredient> foodIngredients) {
        this.offer = offer;
        this.offers = offers;
        this.gtin13 = gtin13;
        this.description = description;
        this.name = name;
        this.sku = sku;
        this.imageUrl = imageUrl;
        this.imageUrls = imageUrls;
        this.foodIngredients = foodIngredients;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getGtin13() {
        return gtin13;
    }

    public void setGtin13(String gtin13) {
        this.gtin13 = gtin13;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<FoodIngredient> getFoodIngredients() {
        return foodIngredients;
    }

    public void setFoodIngredients(List<FoodIngredient> foodIngredients) {
        this.foodIngredients = foodIngredients;
    }
}
