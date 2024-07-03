package com.example.vehiclestore.DAO.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "purchase_orders")
@AllArgsConstructor
public class PurchaseOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "purchase_order_date", nullable = false)
    private Date purchaseOrderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id") 
    private Client client;

    @ManyToMany
    @JoinTable(name = "purchase_order_vehicle",
            joinColumns = @JoinColumn(name = "purchase_order_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<Vehicle> vehicle;

}