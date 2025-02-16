package com.example.CarSalesAgency.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicules") // Ensure this matches the actual table name in the database
@Getter
@Setter // Using @Getter and @Setter instead of @Data to avoid potential pitfalls with @Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false) // Ensure this cannot be null
    private String marque;

    @Column(nullable = false) // Ensure this cannot be null
    private String modele;

    private Integer anneeFabrication;

    private Double prix;

    private Integer kilometrage;

    private Integer miseEnCirculation;

    private String energie;

    private String boiteVitesse;

    private Integer puissanceFiscale;

    private String carrosserie;

    // Constructors, if needed, can be generated manually or using Lombok (@NoArgsConstructor, @AllArgsConstructor)
}


