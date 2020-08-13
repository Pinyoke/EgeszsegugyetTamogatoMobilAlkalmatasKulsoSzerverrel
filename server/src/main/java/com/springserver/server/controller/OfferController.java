package com.springserver.server.controller;

import com.springserver.server.model.Offer;
import com.springserver.server.model.Product;
import com.springserver.server.model.Seller;
import com.springserver.server.model.User;
import com.springserver.server.service.AdminService;
import com.springserver.server.util.JwtTokenUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1")
public class OfferController {

    private final AdminService adminService;


    public OfferController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/offers")
    public Page<Offer> listOffers(){
        System.out.println("OfferController: listOffers");


        return adminService.findActualUserOffers();
    }

    @PostMapping("/offers/create")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) throws IOException {
        System.out.println(offer.getUrl());
        System.out.println(offer.getPrice());
        System.out.println("ITT SZÁLL EL");
        System.out.println(offer.getProductName());


        return ResponseEntity.ok().body(adminService.createOffer(offer));
    }

    @PostMapping("/offers/delete")
    public ResponseEntity<String> deleteOffer(@RequestBody Long id){
        System.out.println("ProductController: deleteOffer");
        System.out.println(id);
        adminService.deleteOfferById(id);
        return ResponseEntity.ok().body("Törölve");
    }

    
}
