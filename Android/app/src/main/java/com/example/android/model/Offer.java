package com.example.android.model;

import androidx.annotation.NonNull;

public class Offer {

    private String url;

    private int price;

    private String sellerName;

    public Offer() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @NonNull
    @Override
    public String toString() {
        return sellerName+": "+price+"Ft";
    }
}
