//package com.MongoBoundary.controllers;
//
//import com.MongoBoundary.enums.RoleEnum;
//import com.MongoBoundary.models.BoundaryUser;
//import com.MongoBoundary.repositories.UserRepo;
//import com.MongoBoundary.security.CurrentUser;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//
//    final UserRepo userRepo;
//
//    final PasswordEncoder passwordEncoder;
//
//    @GetMapping("/user")
//    public BoundaryUser user(@CurrentUser BoundaryUser user){
//        return user;
//    }
//
//    @PostMapping("/registerUser")
//    public ResponseEntity register(@RequestBody BoundaryUser user){
//        try{
//        if (userRepo.findUserByEmail(user.getEmail()) != null){
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь уже зарегестрировал");
//            }
//
//            user.setRoleLevel(RoleEnum.ROLE_USER.getRoleLevel());
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepo.save(user);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно зарегестрирован");
//        } catch (Exception e){
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//
//    }
//}
