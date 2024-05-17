package com.MongoBoundary.controllers;


import com.MongoBoundary.models.BoundaryUser;
import com.MongoBoundary.security.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MainPage {

    @GetMapping
    public String index(@CurrentUser BoundaryUser user) {
        return "Добрый день, " + user.getName();
    }

}
