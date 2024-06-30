package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.vehiclestore.DAO.entities.Authentication;
import com.example.vehiclestore.business.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody Authentication authentication) {
        return authenticationService.login(authentication);
    }

    @PostMapping("/register")
    public Authentication register(@RequestBody Authentication authentication) {
        return authenticationService.register(authentication);
    }
}