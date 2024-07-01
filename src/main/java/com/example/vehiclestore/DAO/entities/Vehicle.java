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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    private Long price;

    private String image;

    private String details;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    private Category category;

}