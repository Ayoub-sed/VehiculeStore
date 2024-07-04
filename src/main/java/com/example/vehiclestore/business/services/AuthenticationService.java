package com.example.vehiclestore.business.services;


import org.springframework.security.core.Authentication;
import com.example.vehiclestore.DAO.entities.User;
import com.example.vehiclestore.exeptions.DuplicateUserException;
import com.example.vehiclestore.web.dto.AuthenticationUserDTO;

public interface AuthenticationService {

    
    User register(User user) throws DuplicateUserException;
    AuthenticationUserDTO login(Authentication authentication);
}