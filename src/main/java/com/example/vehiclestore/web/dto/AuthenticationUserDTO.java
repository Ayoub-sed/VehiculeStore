package com.example.vehiclestore.web.dto;

import java.util.List;

import com.example.vehiclestore.DAO.entities.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public record AuthenticationUserDTO(Long id, String email, List<String> roles) {

    public static AuthenticationUserDTO toAuthenticationUserDTO(User user) {
        List<String> roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        return new AuthenticationUserDTO(user.getId(), user.getEmail(), roles);
    }

}