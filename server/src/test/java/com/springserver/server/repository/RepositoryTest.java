package com.springserver.server.repository;

import com.springserver.server.model.*;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {RepositoryTest.Initializer.class})
public class RepositoryTest {

    @Autowired
    private SensitivityRepository sensitivityRepository;

    @Autowired
    private AllergenRepository allergenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    private String ROLE_USER,USER_EMAIL,USER_NAME,USER_PASSWORD,ALLERGEN_NAME,SENSITIVITY_TYPE,
            PRODUCT_NAME,PRODUCT_GTIN,FOODINGREDIENT_NAME,CONTACTPOINT_TELEPHONE,SELLER_NAME;

    private User user;
    private Product product;
    private Offer offer;
    private Seller seller;


    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("ScanGenTest")
            .withUsername("postgres")
            .withPassword("password");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Before
    public void init() {

        ROLE_USER = "ROLE_USER";
        USER_EMAIL = "emailteszt";
        USER_NAME = "fullnameteszt";
        USER_PASSWORD = "passwordteszt";
        ALLERGEN_NAME = "allergennameteszt";
        SENSITIVITY_TYPE = "sensitivitytypeteszt";
        FOODINGREDIENT_NAME ="foodingredientnameteszt";
        PRODUCT_GTIN = "gtinteszt";
        PRODUCT_NAME = "produtnameteszt";
        CONTACTPOINT_TELEPHONE = "contactpontphoneteszt";
        SELLER_NAME = "sellernameteszt";
        initUserWithRole();
        initProduct();
        initSellerAndOffer();
        initAllergenSensitivity();
    }

    private void initAllergenSensitivity() {
        Allergen allergen = new Allergen();
        allergen.setName(ALLERGEN_NAME);
        List<Sensitivity> sensitivities = new ArrayList<>();
        allergen.setSensitivitys(sensitivities);
        allergenRepository.save(allergen);

        Sensitivity sensitivity = new Sensitivity();
        sensitivity.setUser(user);
        sensitivity.setMyType(SENSITIVITY_TYPE);
        sensitivity.setAllergen(allergen);

        sensitivityRepository.save(sensitivity);
    }

    private void initSellerAndOffer() {
        seller = new Seller();
        ContactPoint contactPoint = new ContactPoint();
        contactPoint.setTelephone(CONTACTPOINT_TELEPHONE);
        contactPoint.setSeller(seller);
        List<ContactPoint> contactPoints = new ArrayList<>();
        contactPoints.add(contactPoint);
        seller.setName(SELLER_NAME);
        seller.setContactPoint(contactPoints);
        sellerRepository.save(seller);

        offer = new Offer();
        offer.setSeller(seller);
        offer.setProduct(product);
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        seller.setOffers(offers);
        product.getOffers().add(offer);

        productRepository.save(product);

    }

    private void initProduct() {
        product = new Product();
        product.setName(PRODUCT_NAME);
        product.setGtin13(PRODUCT_GTIN);

        FoodIngredient foodIngredient = new FoodIngredient();
        foodIngredient.setName(FOODINGREDIENT_NAME);
        foodIngredient.setAlergen(true);
        foodIngredient.setProduct(product);
        List<FoodIngredient> foodIngredients = new ArrayList<>();
        foodIngredients.add(foodIngredient);
        product.setFoodIngredients(foodIngredients);
        List<Offer> offers = new ArrayList<>();
        product.setOffers(offers);

        productRepository.save(product);
    }

    private void initUserWithRole() {
        List<User> userList = new ArrayList<>();
        List<Sensitivity> sensitivities = new ArrayList<>();
        Role role = new Role(ROLE_USER);
        user = new User();

        user.setName(USER_NAME);
        user.setPassword(USER_PASSWORD);
        user.setEmail(USER_EMAIL);
        user.setRole(role);
        user.setSensitivitys(sensitivities);
        userList.add(user);
        role.setUsers(userList);

        roleRepository.save(role);
        userRepository.save(user);

        userRepository.flush();
    }

    @Test
    @Transactional
    public void tesztUser(){
        User userToTest = userRepository.findByEmail(USER_EMAIL);
        Role rolToTeste = roleRepository.findByRole(ROLE_USER);

        assertThat(rolToTeste.getRole()).isEqualTo(ROLE_USER);
        assertThat(userToTest.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(userToTest.getRole().getRole()).isEqualTo(ROLE_USER);

    }

    @Test
    @Transactional
    public void tesztProduct(){
        Product productToTest = productRepository.findByGtin13(PRODUCT_GTIN);
        assertThat(productToTest.getName()).isEqualTo(PRODUCT_NAME);
        assertThat(productToTest.getGtin13()).isEqualTo(PRODUCT_GTIN);
    }

    @Test
    @Transactional
    public void testSeller(){
        Seller seller = sellerRepository.findByName(SELLER_NAME);
        assertThat(seller.getOffers().get(0).getProduct().getName()).isEqualTo(PRODUCT_NAME);
        assertThat(seller.getName()).isEqualTo(SELLER_NAME);
        assertThat(seller.getContactPoint()).isNotEmpty();


    }

    @Test
    @Transactional
    public void testSensitivity(){
        List<Sensitivity> sensitivitiesToTest = sensitivityRepository.findAll();


        assertThat(sensitivitiesToTest.get(0).getMyType()).isEqualTo(SENSITIVITY_TYPE);
        assertThat(sensitivitiesToTest.get(0).getAllergen().getName()).isEqualTo(ALLERGEN_NAME);

    }


}
