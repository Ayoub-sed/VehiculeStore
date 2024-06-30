package com.example.vehiclestore.business.services;

import com.example.vehiclestore.DAO.entities.Authentication;

public interface AuthenticationService {

    public String login(Authentication authentication);
    public Authentication register(Authentication authentication) ;
}