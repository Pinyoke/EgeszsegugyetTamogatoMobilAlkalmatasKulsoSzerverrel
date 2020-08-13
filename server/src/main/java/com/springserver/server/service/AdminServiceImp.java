package com.springserver.server.service;

import com.springserver.server.model.*;
import com.springserver.server.repository.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OfferRepository offerRepository;
    private final AllergenRepository allergenRepository;

    private final static String SELLERADMINROLE="ROLE_SELLERADMIN";
    private final static String ADMINROLE="ROLE_ADMIN";

    public AdminServiceImp(SellerRepository sellerRepository, ProductRepository productRepository, UserRepository userRepository, RoleRepository roleRepository, OfferRepository offerRepository, AllergenRepository allergenRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.offerRepository = offerRepository;
        this.allergenRepository = allergenRepository;
    }

    @Override
    public Page<Allergen> getAllAllergens(String name, int page) {
        System.out.println("AdminService: getAllAllergens");
        Pageable pageable = PageRequest.of(page,4);
        if(this.checkRole("ROLE_ADMIN")){
            System.out.println("Jo a role");
            return allergenRepository.findByNameContaining(name, pageable);
        }else
        {
            System.out.println("Rossz a role");
            return null;
        }


    }




    @Override
    public List<Allergen> getAllergensForMobil() {
        System.out.println("MOBIL ALLERGEN SERVICE");
        return allergenRepository.findAll();
    }

    @Override
    public Page<Product> getAllProduct(String name, int page ) {
        System.out.println("AdminService: getAllProduct");
        System.out.println(page);
        if(this.checkRole(SELLERADMINROLE)) {

            Pageable pageable = PageRequest.of(page, 4);
            System.out.println(productRepository.findByNameContaining(name, pageable));
            return productRepository.findByNameContaining(name, pageable);
        }else{
            return null;
        }
    }

    @Override
    public Allergen createAllergen(Allergen allergen) {
        if(this.checkRole(ADMINROLE)) {
            System.out.println("AdminService: createAllergens");
            return allergenRepository.save(allergen);
        }else {
            return null;
        }
    }

    @Override
    public void deleteAllergen(Long id) {
        if(this.checkRole(ADMINROLE)) {
            System.out.println("AdminService: deleteAllergen");
            allergenRepository.deleteById(id);
        }
    }

    @Override
    public Offer createOffer(Offer offer) {
        if(this.checkRole(SELLERADMINROLE)) {
            User sellerAdmin = this.findActualUser();
            Product product = productRepository.findByName(offer.getProductName());
            product.getOffers().add(offer);
            offer.setProduct(product);
            offer.setSeller(sellerAdmin.getSeller());
            sellerAdmin.getSeller().getOffers().add(offer);
            productRepository.save(product);
            return offer;
        }else{
            return null;
        }
    }

    @Override
    public Page<Offer> findActualUserOffers() {
        System.out.println("AdminService: findActualUserOffers");
        if(this.findActualUser().getRole().getRole().equals("ROLE_ADMIN")){
            Sort sort;
            Pageable pageable = PageRequest.of(0, 4);
            return offerRepository.findAll(pageable);

        }else {
            User actualUser = this.findActualUser();
            List<Offer> offerList = actualUser.getSeller().getOffers();
            for (Offer offer : offerList) {
                offer.setProductName(offer.getProduct().getName());
            }

            Page<Offer> offerPage = new PageImpl<Offer>(offerList, PageRequest.of(0, 1), offerList.size());
            System.out.println(offerPage);

            return offerPage;
        }
    }

    @Override
    public void deleteOfferById(Long id) {
        if(this.checkRole(SELLERADMINROLE)) {
            offerRepository.deleteById(id);
        }
    }

    @Override
    public Page<Seller> getAllSeller(String name, int page) {
        if(this.checkRole(ADMINROLE)) {
            System.out.println("Admin Service: getAllSellers");
            Pageable pageable = PageRequest.of(page, 4);

            System.out.println(sellerRepository.findByNameContaining(name, pageable));
            System.out.println(sellerRepository.findByNameContaining(name,pageable).getContent().size());
            return sellerRepository.findByNameContaining(name, pageable);
        }else{
            return null;
        }
    }

    @Override
    public Seller createSeller(Seller seller) {
        if(this.checkRole(ADMINROLE)) {
            System.out.println("Admi Service: createSeller");
            if (seller.getOffers() == null) {
                List<Offer> offers = new ArrayList<>();
                seller.setOffers(offers);
            }
            if (seller.getContactPoint() == null) {
                List<ContactPoint> contactPoints = new ArrayList<>();
                seller.setContactPoint(contactPoints);

            } else {
                for (ContactPoint contactPoint : seller.getContactPoint()) {
                    contactPoint.setSeller(seller);
                    System.out.println(contactPoint.getTelephone());
                }
            }
            if (seller.getSellerAdmins() == null) {
                List<User> sellerAdmins = new ArrayList<>();
                seller.setSellerAdmins(sellerAdmins);
            }

            return sellerRepository.save(seller);
        }else{
            return null;
        }
    }

    @Override
    public void deleteSeller(Long id) {
        System.out.println("AdminService: deleteSeller");
        Seller seller = sellerRepository.findById(id).orElse(new Seller());
        for (User user: seller.getSellerAdmins()) {
            user.setSeller(null);
            userRepository.save(user);
        }
        sellerRepository.deleteById(id);
    }



    @Override
    public Product createProduct(Product product) {
        System.out.println("AdminService: createProduct: ");
        if(product.getId() == null) {
            System.out.println("UJ");
            if (product.getFoodIngredients() == null) {
                List<FoodIngredient> foodIngredients = new ArrayList<>();
                product.setFoodIngredients(foodIngredients);
            } else {
                for (FoodIngredient foodIngredient : product.getFoodIngredients()) {
                    foodIngredient.setProduct(product);
                }
            }
            if (product.getOffers() == null) {
                List<Offer> offers = new ArrayList<>();
                product.setOffers(offers);
            }

            return productRepository.save(product);
        }else{
            System.out.println("RÉGI");
            return productRepository.save(product);
        }
    }

    @Override
    public Product modifyProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        System.out.println("AdminService: deleteProduct");
        productRepository.deleteById(id);

    }

    @Override
    public Page<User> getAllUsers(String email, int page) {
      /*  List<User> userList = userRepository.findAll();
        for (User user: userList) {
            user.setRoleName(user.getRole().getRole());
            if(user.getSeller() != null) {
                user.setSellerName(user.getSeller().getName());
            }
        }
       */
        if(this.checkRole(ADMINROLE)) {
            Pageable pageable = PageRequest.of(page, 4);
            return userRepository.findByEmailContaining(email, pageable);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        if(this.checkRole(ADMINROLE)) {
            userRepository.deleteById(id);
        }

    }

    @Override
    public User saveUser(User user) {
        if(this.checkRole(ADMINROLE)) {
            System.out.println("Service a modifyUser: ");
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getRole());
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println("Uj jelszo:" + user.getPassword());

            User userToModify = userRepository.findById(user.getId()).orElse(new User());
            userToModify.setName(user.getName());
            userToModify.setEmail(user.getEmail());
            if (user.getRole() != null) {
                System.out.println("BEÁLLITjA A ROLET");
                userToModify.setRole(roleRepository.findByRole(user.getRole().getRole()));
            }
            if (user.getSeller() != null) {
                userToModify.setSeller(sellerRepository.findByName(user.getSeller().getName()));
            }
        /*
        System.out.println("Regi jelszo:" +userToModify.getPassword());
        if(user.getPassword() != userToModify.getPassword()){
            userToModify.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        */

            return userRepository.save(userToModify);
        }else{
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findActualUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }

    private boolean checkRole(String role){
        String userRole = this.findActualUser().getRole().getRole();

        if(userRole.equals(role) || userRole.equals(ADMINROLE)){
            return true;
        }else
        {
            return false;
        }

    }

    @Override
    public Role findActualUserRole() {


        return this.findActualUser().getRole();
    }

    @Override
    public void createRole(Role role) {
        if(role.getUsers() == null){
            List<User> users = new ArrayList<>();
            role.setUsers(users);
        }
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        System.out.println("AdminService: getAllRole");
        return roleRepository.findAll();
    }

    @Override
    public void createAdmins() {
        User admin = userRepository.findByEmail("admin@admin.com");
        User sellerAdmin = userRepository.findByEmail("selleradmin@selleradmin.com");
        admin.setRole(roleRepository.findByRole("ROLE_ADMIN"));
        sellerAdmin.setRole(roleRepository.findByRole("ROLE_SELLERADMIN"));
        userRepository.save(admin);
        userRepository.save(sellerAdmin);

    }
}
