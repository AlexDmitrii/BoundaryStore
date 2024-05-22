//package com.MongoBoundary.controllers;
//
//import com.MongoBoundary.models.Role;
//import com.MongoBoundary.services.RoleService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/roles")
//@RequiredArgsConstructor
//public class RoleController {
//
//    final RoleService roleService;
//
//    final UserDetailsService userDetailsService;
//
//    @PostMapping("/createRole")
//    public String createRole(@RequestBody Role role) {
//        roleService.addRole(role);
//        System.out.println("Here1");
//        return "Role created";
//    }
//
//    @GetMapping
//    public List<Role> getAllRoles() {
//        return roleService.findAll();
//    }
//}
