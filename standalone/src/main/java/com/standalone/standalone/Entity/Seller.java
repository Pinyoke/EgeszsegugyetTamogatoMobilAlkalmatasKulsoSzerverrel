package com.standalone.standalone.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="Sellers")
public class Seller extends BaseEntity{


    private String name;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> sellerAdmins;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactPoint> contactPoint;

    @JsonProperty("@type")
    private String sellerType;

    @Column(unique = true)
    private String url;

    public Seller() {
    }

    public Seller(String name, List<User> sellerAdmins, List<Offer> offers, List<ContactPoint> contactPoint, String sellerType, String url) {
        this.name = name;
        this.sellerAdmins = sellerAdmins;
        this.offers = offers;
        this.contactPoint = contactPoint;
        this.sellerType = sellerType;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getSellerAdmins() {
        return sellerAdmins;
    }

    public void setSellerAdmins(List<User> sellerAdmins) {
        this.sellerAdmins = sellerAdmins;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<ContactPoint> getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(List<ContactPoint> contactPoint) {
        this.contactPoint = contactPoint;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
