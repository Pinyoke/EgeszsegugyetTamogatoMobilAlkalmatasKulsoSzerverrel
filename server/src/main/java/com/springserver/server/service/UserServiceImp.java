package com.springserver.server.service;

import com.springserver.server.model.*;
import com.springserver.server.repository.ProductRepository;
import com.springserver.server.repository.RoleRepository;
import com.springserver.server.repository.SensitivityRepository;
import com.springserver.server.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SensitivityRepository sensitivityRepository;
    private final ProductRepository productRepository;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, SensitivityRepository sensitivityRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sensitivityRepository = sensitivityRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User modify(User user) {
        User userToModify = userRepository.findById(user.getId()).orElse(user);
        if(!user.getPassword().equals(userToModify.getPassword())){
            System.out.println("UJ JELSZO");
         userToModify.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userToModify.setName(user.getName());
        userToModify.setEmail(user.getEmail());
        return userRepository.save(userToModify);
    }

    @Override
    public User save(User user) {
        Role role = roleRepository.findByRole("ROLE_USER");
        role.getUsers().add(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }



    @Override
    public User findActualUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }

    @Override
    public List<Sensitivity> getSensitivitys() {
        User user = this.findActualUser();
        return user.getSensitivitys();
    }

    @Override
    public Sensitivity createSensitivity(Sensitivity sensitivity) {
        User user = this.findActualUser();
        user.getSensitivitys().add(sensitivity);
        sensitivity.setUser(user);
        return sensitivityRepository.save(sensitivity);
    }

    @Override
    public void deleteSensitivity(Long id) {
        sensitivityRepository.deleteById(id);
    }

    @Override
    public ScanResult scan(String gtin13) {
        System.out.println("UserService: SCAN");
         User user = this.findActualUser();
         Product product = productRepository.findByGtin13(gtin13);
         ScanResult scanResult = new ScanResult();
         scanResult.setProduct(product);
       // System.out.println(product.getOffers().get(0).getPrice());
        for (Offer offer: product.getOffers()) {
            offer.setSellerName(offer.getSeller().getName());
        }
         scanResult.setOffers(product.getOffers());
        for (Sensitivity sensitivity: user.getSensitivitys()) {
            System.out.println("Sensitivity: " + sensitivity.getAllergen().getName().toUpperCase());
            for (FoodIngredient foodIngredient: product.getFoodIngredients()) {
                System.out.println("FoodIngredient: " + foodIngredient.getName());
                if(foodIngredient.getName().toUpperCase().contains(sensitivity.getAllergen().getName().toUpperCase())){
                    System.out.println("TALÁLAT");
                    System.out.println();
                    scanResult.setMessage(sensitivity.getMyType());
                }
            }
        }
        if(scanResult.getMessage() == null){
            scanResult.setMessage("Fogyaszthatja a terméket");
        }

        return scanResult;
    }
}
