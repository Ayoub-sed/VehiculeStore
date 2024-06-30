package com.example.vehiclestore.DAO.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class PurchaseOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date purchaseOrderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id") 
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id") 
    private Vehicle vehicle;

}