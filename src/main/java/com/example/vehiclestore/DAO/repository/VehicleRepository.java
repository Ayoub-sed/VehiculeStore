package com.example.vehiclestore.DAO.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.vehiclestore.DAO.entities.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value ="SELECT v FROM Vehicle v WHERE v.category.name = :name")
    List<Vehicle> findByCategoryName(String name);


}