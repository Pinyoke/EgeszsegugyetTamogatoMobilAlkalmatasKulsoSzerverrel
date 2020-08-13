package com.springserver.server.service;

import com.springserver.server.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    //Allergen
    Page<Allergen> getAllAllergens(String name,int page);

    List<Allergen> getAllergensForMobil();

    Allergen createAllergen(Allergen allergen);

    void deleteAllergen(Long id);

    //Offer

    Offer createOffer(Offer offer);

    Page<Offer> findActualUserOffers();

    void deleteOfferById(Long id);

    //Seller
    Page<Seller> getAllSeller(String name, int page);

    Seller createSeller(Seller seller);

    void deleteSeller(Long id);


    //Product
    Page<Product> getAllProduct(String name,int page);

    Product createProduct(Product product);

    void deleteProductById(Long id);

    Product modifyProduct(Product product);

    //User
    Page<User> getAllUsers(String email, int page);

    void deleteUserById(Long id);

    User saveUser(User user);

    User findByEmail(String email);

    User findActualUser();

    Role findActualUserRole();

    //Role

    void createRole(Role role);

    List<Role> getAllRoles();

    void createAdmins();


}
