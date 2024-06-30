package com.example.vehiclestore.DAO.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String details;
    private float price;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    /*
     * Car :private int kilometers;
     * private String fuel;
     * private int power;
     * private int numDoors;
     * private String engineType;
     */

    /*
     * Bike {
     * private String bikeType;
     * private int wheelSize;
     * // Getters and Setters
     */

    /*
     * Motorcycle extends Vehicle {
     * private String motorcycleType;
     * private int kilometers;
     * private int power;
     */
}