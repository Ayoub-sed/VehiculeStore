package com.example.vehiclestore.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclestore.DAO.entities.PurchaseOrder;



@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}