package com.springserver.server.controller;


import com.springserver.server.model.Allergen;
import com.springserver.server.model.Sensitivity;
import com.springserver.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1")
public class SensitivityController {

    private final UserService userService;

    public SensitivityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sensitivitys")
    public ResponseEntity<List<Sensitivity>> getAllergens(){
        System.out.println("MOBIL SENSITIVITY CONTROLLER");
        return ResponseEntity.ok().body(userService.getSensitivitys());
    }

    @PostMapping("/sensitivitys/create")
    public ResponseEntity<Sensitivity> createSeller(@RequestBody Sensitivity sensitivity) throws IOException {
        System.out.println("SensitivityController: create");

        return ResponseEntity.ok().body(userService.createSensitivity(sensitivity));
    }

    @PostMapping("/sensitivitys/delete")
    public ResponseEntity<String> sellerDelete(@RequestBody Long id){
        System.out.println("Controller törlés: " + id);
        System.out.println(id);
        userService.deleteSensitivity(id);
        return ResponseEntity.ok().body("Törölve");
    }



}
