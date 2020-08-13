package com.springserver.server.controller;

import com.springserver.server.model.Role;
import com.springserver.server.model.ScanResult;
import com.springserver.server.model.Sensitivity;
import com.springserver.server.model.User;
import com.springserver.server.service.AdminService;
import com.springserver.server.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final AdminService adminService;

    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostMapping(value = "/user/registration")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("UserController createUser");
        System.out.println(user.getName());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public Page<User> listUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "", required = false) String email){



        return adminService.getAllUsers(email,page);
    }

    @PostMapping("/user/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email){
        System.out.println("UserController: getUserByEmail");

        return ResponseEntity.ok().body(adminService.findByEmail(email));
    }

    @PostMapping("/users/delete")
    public ResponseEntity<String> deleteUser(@RequestBody Long id){
        System.out.println("UserController: deleteUser" + id);
        adminService.deleteUserById(id);
        return ResponseEntity.ok().body("Törölve");
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println("Elkapja SaveUser controller: " + user.getEmail());
        System.out.println(user.getName());
        System.out.println(user.getRole().getRole());

        return ResponseEntity.ok().body(adminService.saveUser(user));
    }

    @PostMapping(value = "/user/mobil/modify")
    public ResponseEntity<User> modifyUser(@RequestBody User user) {
        System.out.println("USERCONTROLLER: modifyUser");
        return ResponseEntity.ok().body(userService.modify(user));
    }

    @GetMapping("/user/getactualuserrole")
    public ResponseEntity<Role> getActualUser(){
        System.out.println("getactualuserole");
        return ResponseEntity.ok().body(adminService.findActualUserRole());
    }


    @GetMapping("/roles")
    public ResponseEntity<java.util.List<Role>> listRoles(){
        System.out.println("Role get Controller");
        return ResponseEntity.ok().body(adminService.getAllRoles());
    }

    @GetMapping("/scanresult/{gtin13}")
    public ResponseEntity<ScanResult> scan(@PathVariable String gtin13){
        System.out.println("UserConroller: SCAN");
        return ResponseEntity.ok().body(userService.scan(gtin13));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getActualUserProfil(){
        System.out.println("UserController");
        return ResponseEntity.ok().body(userService.findActualUser());
    }

    @PostMapping("/users/deletesensitivity/{id}")
    public ResponseEntity<List<Sensitivity>> deleteUserSensitivity(@PathVariable Long id){
        System.out.println("UserController: deleteUserSensitivity: " + id);
        userService.deleteSensitivity(id);
        return ResponseEntity.ok().body(userService.getSensitivitys());
    }


/*
    @GetMapping("/roles")
    public ResponseEntity<User> getLoggedUser(){
        System.out.println("Role get Controller");
        return ResponseEntity.ok().body(adminService.findActualUser());
    }
 */




    @GetMapping("/createrole")
    public void createRole()
    {
        System.out.println("UserController: createRole");
        Role role = new Role();
        role.setRole("ROLE_USER");
        adminService.createRole(role);
        Role role1 = new Role();
        role1.setRole("ROLE_ADMIN");
        adminService.createRole(role1);
        Role role2 = new Role();
        role2.setRole("ROLE_SELLERADMIN");
        adminService.createRole(role2);
    }

    @GetMapping("/createadmins")
    public void createAdmins(){
        adminService.createAdmins();
    }




}
