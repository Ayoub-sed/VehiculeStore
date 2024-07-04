package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclestore.DAO.entities.User;
import com.example.vehiclestore.business.services.AuthenticationService;
import com.example.vehiclestore.business.services.JwtService;
import com.example.vehiclestore.exeptions.DuplicateUserException;
import com.example.vehiclestore.web.dto.AuthenticationUserDTO;
import com.example.vehiclestore.web.dto.RegisterUserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthController {
  private final AuthenticationService authenticationService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  // Constructor for dependency injection
  public AuthController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder,
      JwtService jwtService) {
    this.authenticationService = authenticationService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  // Login API to generate JWT after successful user authentication
  @PostMapping("/login")
  public ResponseEntity<AuthenticationUserDTO> auth(@RequestBody Authentication authentication) {
    // Authenticate the user and generate the authenticated user DTO
    AuthenticationUserDTO userDTO = authenticationService.login(authentication);
    // Issue JWT after successful authentication
    ResponseCookie jwtToken = jwtService.generateJwtCookie(jwtService.generateToken(authentication));
    // Return the JWT token in the login response
    // MODIFIED http
    return ResponseEntity.ok()
        .header("Set-Cookie", jwtToken.toString())
        .body(userDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<RegisterUserDTO> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws DuplicateUserException{
    User user = authenticationService.register(RegisterUserDTO.fromRegisterUserDTO(registerUserDTO, passwordEncoder));
    // Encode the user password before saving it
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // Register the user and return the registered user DTO
    return ResponseEntity.ok().body(RegisterUserDTO.toRegisterUserDTO(user));
  }

  // Endpoint for user logout (sign-out)
@PostMapping("/signout")
public ResponseEntity<Void> logout(HttpServletRequest request) {
    // Generate a clean JWT cookie to remove the existing one
    ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();

    // Return the response with the clean JWT cookie in the headers
    return ResponseEntity.ok()
            .header("Set-Cookie", jwtCookie.toString())
            .build();
}
}