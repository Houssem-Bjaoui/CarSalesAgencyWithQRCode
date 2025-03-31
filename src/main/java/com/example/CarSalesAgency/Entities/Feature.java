package com.example.CarSalesAgency.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "features")
@Data
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}