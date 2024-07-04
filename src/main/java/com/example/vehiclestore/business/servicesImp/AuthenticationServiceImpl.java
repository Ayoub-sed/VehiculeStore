package com.example.vehiclestore.business.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.vehiclestore.business.services.AuthenticationService;
import com.example.vehiclestore.exeptions.DuplicateUserException;
import com.example.vehiclestore.web.dto.AuthenticationUserDTO;

import com.example.vehiclestore.DAO.entities.User;
import com.example.vehiclestore.DAO.repository.AuthenticationRepository;
import com.example.vehiclestore.DAO.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    // Repository to handle User entity persistence
    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws DuplicateUserException {

        if (user == null) {
            return null;
        }

        try{
        return userRepository.save(user);
        }catch(Exception e){
            throw new DuplicateUserException("User already exists");
        }
    }
    @Override
    public AuthenticationUserDTO login(Authentication authentication) {

        // Retrieve the user principal from the authentication object after basic
        // authentica
        User user = (User) authentication.getPrincipal();

        // Convert the User entity to AuthenticationUserDTO and return it
        return AuthenticationUserDTO.toAuthenticationUserDTO(user);
    }

   
}