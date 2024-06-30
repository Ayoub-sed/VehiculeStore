package com.example.vehiclestore.DAO.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclestore.DAO.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}