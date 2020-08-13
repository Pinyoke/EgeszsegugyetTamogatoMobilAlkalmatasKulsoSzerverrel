package com.springserver.server.controller;

import com.springserver.server.model.Allergen;
import com.springserver.server.model.Seller;
import com.springserver.server.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1")
public class AllergenController {

    private final AdminService adminService;

    public AllergenController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/allergens")
    public Page<Allergen> listSellers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "", required = false) String name){
        System.out.println("AllergenController: listAllergens");
        return adminService.getAllAllergens(name,page);
    }

    @GetMapping("/getallergens")
    public ResponseEntity<List<Allergen>> getAllergens(){
        System.out.println("MOBIL ALLERGEN CONTROLLER");
        return ResponseEntity.ok().body(adminService.getAllergensForMobil());
    }

    @PostMapping("/allergens/create")
    public ResponseEntity<Allergen> createSeller(@RequestBody Allergen allergen) throws IOException {
        System.out.println("SellerController: create");

        return ResponseEntity.ok().body(adminService.createAllergen(allergen));
    }

    @PostMapping("/allergens/delete")
    public ResponseEntity<String> sellerDelete(@RequestBody Long id){
        System.out.println("Controller törlés: " + id);
        System.out.println(id);
        adminService.deleteAllergen(id);
        return ResponseEntity.ok().body("Törölve");
    }



}
