package com.springserver.server.model;

import java.util.ArrayList;
import java.util.List;

public class ScanResult {

    private String message;
    private Product product;
    private List<Offer> offers = new ArrayList<>();

    public ScanResult() {
    }

    public ScanResult(String message, Product product, List<Offer> offers) {
        this.message = message;
        this.product = product;
        this.offers = offers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
