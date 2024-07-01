package com.example.vehiclestore.business.servicesImp;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort; 
import org.springframework.data.domain.Sort.Direction;

import com.example.vehiclestore.business.services.VehicleService;
import com.example.vehiclestore.exeptions.ResourceNotFound;
import com.example.vehiclestore.DAO.entities.Category;
import com.example.vehiclestore.DAO.entities.Vehicle;
import com.example.vehiclestore.DAO.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) throws ResourceNotFound {
        if (id == null) {
            return null;
        }
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("product not found for id :" + id));

    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) throws ResourceNotFound {
        if (vehicleRepository.existsById(id)) {
            vehicle.setId(id);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    @Override
    public void deleteVehicle(Long id) throws ResourceNotFound {
        if (id == null) {
            return;
        } else if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFound("product not found for id :" + id);
        } else
            vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> findByCategoryName(String categoryName) {
        return vehicleRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Vehicle> sortedByPrice(String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sort)) {
            direction = Sort.Direction.DESC;
        }
        return vehicleRepository.findAll(Sort.by(direction, "price"));
    }

    @Override
    public List<Vehicle> sortedByName(String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sort)) {
            direction = Sort.Direction.DESC;
        }
        return vehicleRepository.findAll(Sort.by(direction, "name"));

    }

    @Override
    public Page<Vehicle> getAllVehiclesPagination(Pageable pageable){
        if (pageable == null) {
            return null;
        }
        return this.vehicleRepository.findAll(pageable);
    }
}
// return personRepository.findAll(Sort.by(Direction.ASC, "age"));

/*
 * @Override
 * public List<Product> search(Category category , String name) {
 * return productRepository.findByCategoryAndName(category,name);
 * }
 */
/*
 * @Override
 * public List<Vehicle> search(Category category, String name) {
 * return vehicleRepository.findByCategoryAndName(category,name);
 * }
 */

/*
 * @Override
 * public List<Vehicle> advancedSearch(Category category, String namePrefix) {
 * return vehicleRepository.search(category,namePrefix);
 * 
 * }
 */
