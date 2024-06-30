package com.example.vehiclestore.DAO.entities;


import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authentication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
}