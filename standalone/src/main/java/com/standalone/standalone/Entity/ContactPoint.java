package com.standalone.standalone.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="ContactPoints")
@SequenceGenerator(name = "default_gen", sequenceName = "contact_seq", allocationSize = 1)
public class ContactPoint extends BaseEntity {

    private String areaServed;

    private String telephone;

    private String availableLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    public ContactPoint() {
    }

    public ContactPoint(String areaServed, String telephone, String availableLanguage, Seller seller) {
        this.areaServed = areaServed;
        this.telephone = telephone;
        this.availableLanguage = availableLanguage;
        this.seller = seller;
    }

    public String getAreaServed() {
        return areaServed;
    }

    public void setAreaServed(String areaServed) {
        this.areaServed = areaServed;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvailableLanguage() {
        return availableLanguage;
    }

    public void setAvailableLanguage(String availableLanguage) {
        this.availableLanguage = availableLanguage;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
