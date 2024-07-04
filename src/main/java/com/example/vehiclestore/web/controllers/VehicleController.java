package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclestore.DAO.entities.Category;
import com.example.vehiclestore.DAO.entities.Vehicle;
import com.example.vehiclestore.business.services.CategoryService;
import com.example.vehiclestore.business.services.VehicleService;
import com.example.vehiclestore.exeptions.ResourceNotFound;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/vehicles")
@PreAuthorize("hasRole('ADMIN','USER')")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN','USER') and hasAuthority('READ_PRIVILEGE')")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Vehicle getVehicleById(@PathVariable Long id) throws ResourceNotFound {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        String categoryName = vehicle.getCategory().getName();
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            throw new EntityNotFoundException("Category not found");
        } else {
            vehicle.setCategory(category);
            return vehicleService.createVehicle(vehicle);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('UPDATE_PRIVILEGE')")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) throws ResourceNotFound {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('DELETE_PRIVILEGE')")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) throws ResourceNotFound {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(vehicleService.getClass().getName() + " is deleted", HttpStatus.OK);
    }

    // getVehiculesSortedByPrice
    @GetMapping("/filterByPrice")
    @PreAuthorize("hasRole('ADMIN','USER') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Object> sortedByPrice(@RequestParam(required =false, defaultValue = "asc") String sort) {
        List<Vehicle> sortedVehicles = vehicleService.sortedByPrice(sort);
        return new ResponseEntity<>(sortedVehicles, HttpStatus.OK);
    }

    // getVehiculesSortedByName
    @GetMapping("/filterByName")
    @PreAuthorize("hasRole('ADMIN','USER') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Object> sortedByName(@RequestParam(required =false, defaultValue = "asc") String sort) {
        List<Vehicle> sortedVehicles = vehicleService.sortedByName(sort);
        return new ResponseEntity<>(sortedVehicles, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    @PreAuthorize("hasRole('ADMIN','USER') and hasAuthority('READ_PRIVILEGE')")
    public List<Vehicle> getByCategory(@PathVariable String categoryName) {
        return vehicleService.findByCategoryName(categoryName);
    }

 @GetMapping("/pagination")
 @PreAuthorize("hasRole('ADMIN','USER') and hasAuthority('READ_PRIVILEGE')")
 public Page<Vehicle> getAllVehiclesPagination(@RequestParam (defaultValue = "0") int page,@RequestParam (defaultValue = "2") int pageSize){  

    Page<Vehicle> vehiculePage=this.vehicleService.getAllVehiclesPagination(PageRequest.of(page, pageSize));
    return vehiculePage;
}    }
 

/*
 * @GetMapping("/advancedSearch")
 * public List<Vehicle> advancedSearch(@RequestParam Category
 * category, @RequestParam String namePrefix) {
 * return vehicleService.advancedSearch(category, namePrefix);
 * }
 */

/*
 * @GetMapping("/sorted")
 * public ResponseEntity<Object> getVehiculesSortedByName() {
 * List<Vehicle> sortedVehicles = vehicleService.getAllVehicles();
 * sortedVehicles.sort((v1, v2) -> v1.getName().compareTo(v2.getName()));
 * return new ResponseEntity<>(sortedVehicles, HttpStatus.OK);
 * }
 * 
 * @GetMapping("/sortedByPrice")
 * public ResponseEntity<Object> getVehiculesSortedByPrice() {
 * List<Vehicle> sortedVehicles = vehicleService.getAllVehicles();
 * 
 * return new ResponseEntity<>(sortedVehicles, HttpStatus.OK);
 * }
 */
