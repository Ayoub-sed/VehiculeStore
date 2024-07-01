package com.example.vehiclestore.DAO.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "purchase_orders")
@AllArgsConstructor
public class PurchaseOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date purchaseOrderDate;
    /* /*  public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    } */ 
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id") 
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id") 
    private Vehicle vehicle;

}