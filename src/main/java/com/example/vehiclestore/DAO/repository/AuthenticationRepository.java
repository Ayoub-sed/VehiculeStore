package com.example.vehiclestore.DAO.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclestore.DAO.entities.Authentication;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    Authentication findByUsernameAndPassword(String username, String password);
}