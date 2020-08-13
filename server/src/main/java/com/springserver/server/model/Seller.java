package com.springserver.server.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="Sellers")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Seller extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> sellerAdmins;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactPoint> contactPoint;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Offer> offers = new ArrayList<>();

    @JsonProperty("sellerType")
    private String sellerType;

    @Column(unique = true)
    private String url;

    public Seller() {
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
