package com.example.android.model;

import java.util.ArrayList;
import java.util.List;

public class ScanResult {

    private Product product;

    private String message;

    private List<Offer> offers = new ArrayList<>();

    public ScanResult() {
    }

    public ScanResult(Product product, String message, List<Offer> ofers) {
        this.product = product;
        this.message = message;
        this.offers = ofers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
