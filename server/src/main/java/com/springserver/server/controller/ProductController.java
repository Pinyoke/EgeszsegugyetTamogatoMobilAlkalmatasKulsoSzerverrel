package com.springserver.server.controller;

import com.springserver.server.model.Product;
import com.springserver.server.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1")
public class ProductController {

    private final AdminService adminService;

    public ProductController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/products")
    public Page<Product> listProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "", required = false) String name){
        System.out.println("ProductController: listProducts");

        return adminService.getAllProduct(name,page);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws IOException {
        System.out.println("ProductController: createProduct");
        System.out.println(product.getName());
        System.out.println(product.getSku());
        System.out.println(product.getDescription());
        System.out.println(product.getGtin13());
        System.out.println(product.getImageUrl());
        return ResponseEntity.ok().body(adminService.createProduct(product));
    }

    @PostMapping("/products/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody Long id){
        System.out.println("ProductController: deleteProduct");
        System.out.println(id);
        adminService.deleteProductById(id);
        return ResponseEntity.ok().body("Törölve");
    }

    @PostMapping("/products/modify")
    public ResponseEntity<Product> modifyProduct(@RequestBody Product product) throws IOException {
        System.out.println("ProductController: createProduct");
        System.out.println(product.getName());
        System.out.println(product.getSku());
        System.out.println(product.getDescription());
        System.out.println(product.getGtin13());
        System.out.println(product.getImageUrl());
        return ResponseEntity.ok().body(adminService.createProduct(product));
    }
}
