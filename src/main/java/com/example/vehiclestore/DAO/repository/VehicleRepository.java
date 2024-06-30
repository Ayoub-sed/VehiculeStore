package com.example.vehiclestore.DAO.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclestore.DAO.entities.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}