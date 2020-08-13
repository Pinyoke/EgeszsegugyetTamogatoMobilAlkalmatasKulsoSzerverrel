package com.standalone.standalone.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Users")
public class User extends BaseEntity{

    private String name;

    private String email;

    private String password;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }




}
