package com.example.vehiclestore.business.services;

import com.example.vehiclestore.DAO.entities.Vehicle;

import java.util.List;

public interface VehicleService {

    public List<Vehicle> getAllVehicles();

    public Vehicle getVehicleById(Long id) ;

    public Vehicle createVehicle(Vehicle vehicle) ;

    public Vehicle updateVehicle(Long id, Vehicle vehicle) ;

    public void deleteVehicle(Long id) ;
    

}