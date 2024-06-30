package com.example.vehiclestore.business.servicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehiclestore.business.services.AuthenticationService;
import com.example.vehiclestore.DAO.entities.Authentication;
import com.example.vehiclestore.DAO.repository.AuthenticationRepository;



@Service
public class AuthenticationServiceImp implements AuthenticationService{

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public String login(Authentication authentication) {
        Authentication auth = authenticationRepository.findByUsernameAndPassword(authentication.getUsername(), authentication.getPassword());
        if (auth != null) {
            return "Login successful";
        }
        return "Invalid credentials";
    }

    public Authentication register(Authentication authentication) {
        return authenticationRepository.save(authentication);
    }
}