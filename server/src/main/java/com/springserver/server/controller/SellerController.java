package com.springserver.server.controller;

import com.springserver.server.model.Seller;
import com.springserver.server.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1")
public class SellerController {

    private final AdminService adminService;

    public SellerController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/sellers")
    public Page<Seller> listSellers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "", required = false) String name){
        System.out.println("SellerController: listSellers");
        return adminService.getAllSeller(name, page);
    }

    @PostMapping("/sellers/create")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws IOException {
        System.out.println("SellerController: create");

        return ResponseEntity.ok().body(adminService.createSeller(seller));
    }

    @PostMapping("/sellers/delete")
    public ResponseEntity<String> sellerDelete(@RequestBody Long id){
        System.out.println("Controller törlés: " + id);
        System.out.println(id);
        adminService.deleteSeller(id);
        return ResponseEntity.ok().body("Törölve");


    }

}
