package com.example.vehiclestore.business.services;

import com.example.vehiclestore.DAO.entities.Category;
import com.example.vehiclestore.DAO.entities.Vehicle;
import com.example.vehiclestore.exeptions.ResourceNotFound;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {

    public List<Vehicle> getAllVehicles();

    public Vehicle getVehicleById(Long id) throws ResourceNotFound;

    public Vehicle createVehicle(Vehicle vehicle) ;

    public Vehicle updateVehicle(Long id, Vehicle vehicle)  throws ResourceNotFound;

    public void deleteVehicle(Long id) throws ResourceNotFound;

    public List<Vehicle> findByCategoryName(String categoryName);

    public List<Vehicle> sortedByPrice(String sort);

    public List<Vehicle> sortedByName(String sort);

    public Page<Vehicle> getAllVehiclesPagination(Pageable pageable);

/*     public List<Vehicle> getByCategory(Category category,String name);
 */
    /* public List<Vehicle> advancedSearch(Category category, String namePrefix); */
    
}